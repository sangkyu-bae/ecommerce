package com.example.order.adapter.out.external.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DeliveryEvent {

    private Long sizeId;

    private Long userId;

    private String address;

    private Long orderId;

}
