package com.example.adminservice.adapter.axon.aggregate;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;
import com.example.adminservice.adapter.axon.event.ProductCreateEvent;
import com.example.adminservice.application.port.out.brand.UpdateProductSizePort;
import com.example.adminservice.domain.SizeVo;
import com.example.adminservice.infra.error.ErrorException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.event.CheckRegisteredProductCommand;
import org.example.event.CheckRegisteredProductEvent;
import org.example.event.rollback.RollbackProductFinishedEvent;
import org.example.event.rollback.RollbackRequestProductCommand;

import java.util.List;
import java.util.stream.Collectors;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@Data
@Slf4j
public class ProductAggregate {

    @AggregateIdentifier
    private String id;

    private String name;

    private int price;

    private String description;

    private String productImage;


    @CommandHandler
    public ProductAggregate(ProductCreateCommand command) {

        log.info("ProductCreateEvent Sourcing Handler!");
        apply(new ProductCreateEvent(
                command.getName(),
                command.getPrice(),
                command.getDescription(),
                command.getProductImage(),
                command.getAggregateIdentifier()
        ));
    }

    @CommandHandler
    public void handler(CheckRegisteredProductCommand command, UpdateProductSizePort port) {
        log.info("CheckRegisteredProductCommand Handler");

        id = command.getAggregateIdentifier();

        boolean isSuccess = true;

        try{
            for(CheckRegisteredProductCommand.ProductRequestCreateCommand productReq :command.getProductRequestCreateEvents()){
                SizeVo sizeVo = SizeVo.createGenerateSizeVo(
                        new SizeVo.SizeId(productReq.getSizeId()),
                        new SizeVo.Size(0),
                        new SizeVo.Quantity(productReq.getAmount())
                );

                port.updateProductSize(sizeVo);
            }
        }catch (ErrorException e){
            isSuccess = false;
        }

        List<CheckRegisteredProductEvent.ProductRequestCreateCommand> productRequestCreateEvents = command.getProductRequestCreateEvents().stream()
                        .map(product->new CheckRegisteredProductEvent.ProductRequestCreateCommand(product.getSizeId(), product.getAmount(), product.getCouponId()))
                        .collect(Collectors.toList());

        apply(new CheckRegisteredProductEvent(
                command.getCreateOrderId(),
                isSuccess,
                command.getCheckRegisteredProductIdAndAmount(),
                command.getUserId(),
                command.getAggregateIdentifier(),
                productRequestCreateEvents
        ));
    }

    @CommandHandler
    public void handler(RollbackRequestProductCommand command, UpdateProductSizePort port) {
        log.info("RollbackRequestProductCommand Handler");

        log.info("command : {}",command);
        id = command.getAggregateIdentifier();


        try{
            SizeVo sizeVo = SizeVo.createGenerateSizeVo(
                    new SizeVo.SizeId(command.getSizeId()),
                    new SizeVo.Size(0),
                    new SizeVo.Quantity(-command.getAmount())
            );

            port.updateProductSize(sizeVo);
        }catch (Exception e){
            log.error("error : {} ", e);
        }


        apply(new RollbackProductFinishedEvent(
                command.getRollbackProductId(),
                command.getAggregateIdentifier(),
                command.getSizeId(),
                command.getAmount()
        ));

    }

    @EventSourcingHandler
    public void on(ProductCreateEvent event) {
        log.info("ProductCreateEvent Sourcing Handler");
        id = event.getAggregateIdentifier();
        name = event.getName();
        price = event.getPrice();
        description = event.getDescription();
        productImage = event.getProductImage();
    }




    public ProductAggregate() {

    }
}
