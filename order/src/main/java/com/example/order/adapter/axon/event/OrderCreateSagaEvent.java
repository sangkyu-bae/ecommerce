package com.example.order.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateSagaEvent {
    private String aggregateIdentifier;

    private String userId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;
}
