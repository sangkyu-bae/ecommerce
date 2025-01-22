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

    private int payment;

}
