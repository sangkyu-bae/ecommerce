package com.example.delivery.adapter.out.persistance;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.adapter.out.persistance.repository.DeliveryEntityRepository;
import com.example.delivery.application.port.out.RegisterDeliveryPort;
import com.example.delivery.common.PersistenceAdapter;
import com.example.delivery.domain.delivery.DeliveryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersistenceAdapter implements RegisterDeliveryPort {

    private final DeliveryEntityRepository deliveryEntityRepository;

    @Override
    public DeliveryEntity registerDelivery(DeliveryVo deliveryVo) {
        DeliveryEntity createDelivery = DeliveryEntity.builder()
                .sizeId(deliveryVo.getSizeId())
                .userId(deliveryVo.getUserId())
                .orderId(deliveryVo.getOrderId())
                .address(deliveryVo.getAddress())
                .status(deliveryVo.getStatus())
                .createAt(deliveryVo.getCreateAt())
                .updateAt(deliveryVo.getUpdateAt())
                .build();
        return deliveryEntityRepository.save(createDelivery);
    }
}
