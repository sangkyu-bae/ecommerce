package com.example.order.adapter.axon.aggregate;

import com.example.order.adapter.axon.command.OrderRequestCreateSagaCommand;
import com.example.order.adapter.axon.event.OrderCreateSagaEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
@Aggregate()
@Data
public class OrderNewAggregate {

    @AggregateIdentifier
    private String id;

    private String userId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    @CommandHandler
    public OrderNewAggregate(OrderRequestCreateSagaCommand command){

        apply(new OrderCreateSagaEvent());
    }

    @EventSourcingHandler
    public void on (OrderCreateSagaEvent event){
        this.id = event.getAggregateIdentifier();
        this.userId = event.getUserId();
        this.sizeId = event.getSizeId();
        this.amount = event.getAmount();
        this.payment = event.getPayment();
        this.address = event.getAddress();
    }
}
