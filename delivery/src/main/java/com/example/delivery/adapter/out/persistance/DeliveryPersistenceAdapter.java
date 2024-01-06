package com.example.delivery.adapter.out.persistance;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.adapter.out.persistance.repository.DeliveryEntityRepository;
import com.example.delivery.application.port.out.FindDeliveryPort;
import com.example.delivery.application.port.out.RegisterDeliveryPort;

import com.example.delivery.application.port.out.UpdateDeliveryPort;
import com.example.delivery.domain.DeliveryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersistenceAdapter implements RegisterDeliveryPort, FindDeliveryPort, UpdateDeliveryPort {

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

    @Override
    public DeliveryEntity findDeliveryById(DeliveryVo.DeliveryId deliveryId) {
        //오류 공통화
        return deliveryEntityRepository.findById(deliveryId.getId()).orElseThrow();
    }

    @Override
    public DeliveryEntity updateDelivery(DeliveryVo deliveryVo) {
        DeliveryEntity findDeliveryEntity = findDeliveryById(new DeliveryVo.DeliveryId(deliveryVo.getId()));

        Optional.ofNullable(deliveryVo.getStatus())
                .ifPresent(findDeliveryEntity::setStatus);

        Optional.ofNullable(deliveryVo.getAddress())
                .ifPresent(findDeliveryEntity::setAddress);

        findDeliveryEntity.setUpdateAt(deliveryVo.getUpdateAt());

        return deliveryEntityRepository.save(findDeliveryEntity);
    }
}
