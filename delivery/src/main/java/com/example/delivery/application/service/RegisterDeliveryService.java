package com.example.delivery.application.service;

import com.example.delivery.adapter.out.persistance.DeliveryMapper;
import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.application.port.in.command.RegisterDeliveryCommand;
import com.example.delivery.application.port.in.usecase.RegisterDeliveryUseCase;
import com.example.delivery.application.port.out.RegisterDeliveryPort;
import com.example.delivery.domain.DeliveryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RegisterDeliveryService implements RegisterDeliveryUseCase {

    private final RegisterDeliveryPort registerDeliveryPort;
    private final DeliveryMapper deliveryMapper;
    @Override
    public DeliveryVo registerDelivery(RegisterDeliveryCommand command) {
        int status = DeliveryVo.StatusCode.READY.getStatus();
        DeliveryVo deliveryVo = DeliveryVo.createGenerateDeliveryVo(
                new DeliveryVo.DeliveryId(0),
                new DeliveryVo.DeliverySizeId(command.getSizeId()),
                new DeliveryVo.DeliveryUserId(command.getUserId()),
                new DeliveryVo.DeliveryOrderId(command.getOrderId()),
                new DeliveryVo.DeliveryAddress(command.getAddress()),
                new DeliveryVo.DeliveryStatus(status),
                new DeliveryVo.DeliveryCreateAt(LocalDate.now()),
                new DeliveryVo.DeliveryUpdateAt(LocalDate.now())
        );
        DeliveryEntity createDelivery = registerDeliveryPort.registerDelivery(deliveryVo);

        return deliveryMapper.DeliveryEntityToDelivery(createDelivery);
    }
}
