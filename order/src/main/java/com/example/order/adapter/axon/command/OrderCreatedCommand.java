package com.example.order.adapter.axon.command;

import lombok.*;
import org.example.SelfValidating;

@Builder @Data
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class OrderCreatedCommand extends SelfValidating<OrderCreatedCommand> {

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private int status;

    private long userId;
}
