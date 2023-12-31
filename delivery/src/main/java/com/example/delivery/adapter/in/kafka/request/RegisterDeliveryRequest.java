package com.example.delivery.adapter.in.kafka.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterDeliveryRequest {

    private Long sizeId;

    private String userId;

    private String address;

    private int quantity;

    private Long orderId;
}
