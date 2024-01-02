package com.example.delivery.adapter.in.kafka.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterDeliveryEvent {

    private Long sizeId;

    private Long userId;

    private String address;

    private Long orderId;
}
