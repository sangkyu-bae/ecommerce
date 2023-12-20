package com.example.demo.adapter.in.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegisteredMemberEvent {

    private long memberId;

    private String createOrderId;

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private boolean status;
}
