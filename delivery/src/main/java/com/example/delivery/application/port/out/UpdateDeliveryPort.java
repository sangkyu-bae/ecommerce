package com.example.delivery.application.port.out;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.domain.DeliveryVo;

public interface UpdateDeliveryPort {
    DeliveryEntity updateDelivery(DeliveryVo deliveryVo);
}
