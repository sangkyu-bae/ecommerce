package com.example.order.domain.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DeliveryEvent {

    private Long sizeId;

    private Long userId;

    private String address;

    private String orderId;

    public static DeliveryEvent createDeliveryEvent(Long sizeId, Long userId, String address, String orderId){
        return new DeliveryEvent(
                sizeId,
                userId,
                address,
                orderId
        );
    }

}
