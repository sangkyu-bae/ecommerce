package com.example.order.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestCreateCommand {

    private String createOrderId;

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private int status;

    private long userId;
}
