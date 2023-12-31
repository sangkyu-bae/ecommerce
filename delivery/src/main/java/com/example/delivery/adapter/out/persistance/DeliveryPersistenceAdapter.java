package com.example.delivery.adapter.out.persistance;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.adapter.out.persistance.repository.DeliveryEntityRepository;
import com.example.delivery.application.port.out.RegisterDeliveryPort;

import com.example.delivery.domain.DeliveryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<DeliveryEntity> bulkRegisterDelivery(List<DeliveryVo> deliveryVos) {

        List<DeliveryEntity> deliveryEntityList = deliveryVos.stream()
                .map(deliveryVo -> DeliveryEntity.builder()
                        .sizeId(deliveryVo.getSizeId())
                        .userId(deliveryVo.getUserId())
                        .orderId(deliveryVo.getOrderId())
                        .address(deliveryVo.getAddress())
                        .status(deliveryVo.getStatus())
                        .createAt(deliveryVo.getCreateAt())
                        .updateAt(deliveryVo.getUpdateAt())
                        .build())
                .collect(Collectors.toList());

        return deliveryEntityRepository.saveAll(deliveryEntityList);
    }
}
