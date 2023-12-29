package com.example.order.adapter.axon.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrderCreatedEvent {

    private String createOrderId;

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private Long couponId;
}
