package com.example.delivery.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UpdateDeliveryRequest {
    private int status;

    private String address;
}
