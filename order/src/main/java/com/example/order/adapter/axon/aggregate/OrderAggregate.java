package com.example.order.adapter.axon.aggregate;


import com.example.order.adapter.axon.command.OrderRemoveByEventCommand;
import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.adapter.axon.command.ProductRequestCommand;
import com.example.order.adapter.axon.event.OrderCreateEvent;
import com.example.order.adapter.axon.event.RegisterOrderCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;
import java.util.stream.Collectors;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@Data
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String id;

    private long userId;


    @CommandHandler
    public OrderAggregate(OrderRequestCreateCommand command){
        System.out.println("RechargingMoneyRequestCreateCommand Handler");

        log.info("order ID : {}", command.getCreateOrderId());
        log.info( "user ID : {}", command.getUserId());
        //axon에 이벤트 생성하여 식별자를 만들 위한 event 소싱
        apply(new OrderCreateEvent(
                command.getCreateOrderId(),
                command.getUserId()
        ));

        // Saga Start
        apply(new RegisterOrderCreatedEvent(
                command.getCreateOrderId(),
                command.getPayment(),
                command.getAddress(),
                command.getStatus(),
                command.getUserId(),
                command.getProductRequestCommands()
        ));
    }

    @CommandHandler
    public void handler(OrderRemoveByEventCommand command){

    }

    @EventSourcingHandler
    public void on (OrderCreateEvent event){
        this.id = event.getAggregateIdentifier();
        this.userId = event.getUserId();
    }

    public OrderAggregate(){

    }
}
