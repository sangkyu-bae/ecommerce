package com.example.order.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderCreateEvent {

    private String aggregateIdentifier;

    private long userId;
}
