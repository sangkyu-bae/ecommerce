package com.example.delivery.adapter.out.persistance;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.domain.delivery.DeliveryVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryMapper {

    private final ModelMapper modelMapper;

    public DeliveryVo DeliveryEntityToDelivery(DeliveryEntity deliveryEntity){
        return DeliveryVo.createGenerateDeliveryVo(
                new DeliveryVo.DeliveryId(deliveryEntity.getId()),
                new DeliveryVo.DeliverySizeId(deliveryEntity.getSizeId()),
                new DeliveryVo.DeliveryUserId(deliveryEntity.getUserId()),
                new DeliveryVo.DeliveryOrderId(deliveryEntity.getOrderId()),
                new DeliveryVo.DeliveryAddress(deliveryEntity.getAddress()),
                new DeliveryVo.DeliveryStatus(deliveryEntity.getStatus()),
                new DeliveryVo.DeliveryCreateAt(deliveryEntity.getCreateAt()),
                new DeliveryVo.DeliveryUpdateAt(deliveryEntity.getUpdateAt())
        );
    }
}
