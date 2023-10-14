package com.example.delivery.application.port.out;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.domain.delivery.DeliveryVo;

public interface RegisterDeliveryPort {
    DeliveryEntity registerDelivery(DeliveryVo deliveryVo);
}
