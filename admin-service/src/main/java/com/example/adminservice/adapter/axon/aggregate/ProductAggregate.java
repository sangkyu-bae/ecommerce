package com.example.adminservice.adapter.axon.aggregate;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;
import com.example.adminservice.adapter.axon.event.ProductCreateEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

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
    public void handler(ProductCreateCommand command){
        ProductCreateEvent event = ProductCreateEvent.builder()
                .description(command.getDescription())
                .price(command.getPrice())
                .productImage(command.getProductImage())
                .name(command.getName())
                .aggregateIdentifier(command.getAggregateIdentifier())
                .build();

        apply(event);
    }

    @EventSourcingHandler
    public void on (ProductCreateEvent event){
        log.info("ProductCreateEvent Sourcing Handler");
        id = event.getAggregateIdentifier();
        name = event.getName();
        price = event.getPrice();
        description = event.getDescription();
        productImage = event.getProductImage();
    }

}
