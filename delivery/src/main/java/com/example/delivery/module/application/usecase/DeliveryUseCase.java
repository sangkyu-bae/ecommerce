package com.example.delivery.module.application.usecase;

import com.example.delivery.module.domain.delivery.dto.DeliveryDto;
import com.example.delivery.module.domain.delivery.entity.Delivery;
import com.example.delivery.module.domain.delivery.service.DeliveryReadService;
import com.example.delivery.module.domain.delivery.service.DeliveryWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryUseCase {

    private final DeliveryWriteService deliveryWriteService;

    private final DeliveryReadService deliveryReadService;

    public Delivery createDelivery(DeliveryDto deliveryDto){
        Delivery createDelivery = deliveryWriteService.create(deliveryReadService.toEntity(deliveryDto));
        return createDelivery;
    }

}
