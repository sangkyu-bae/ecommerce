package com.example.order.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrderRequest {


    List<RegisterOrderProductRequest> products;

    private String address;

    private String phone;
//    private long productId;
//
//    private long colorId;
//
//    private long sizeId;
//
//    private int amount;
//
    private int payment;
//
//    private String address;
//
//    private Long couponId;

}
