package com.example.order.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull
public class RegisterOrderRq {

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private Long couponId;
}
