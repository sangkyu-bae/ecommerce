package com.example.delivery.application.service;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import com.example.delivery.application.port.in.command.UpdateDeliveryCommand;
import com.example.delivery.application.port.in.usecase.UpdateDeliveryUseCase;
import com.example.delivery.application.port.out.FindDeliveryPort;
import com.example.delivery.application.port.out.UpdateDeliveryPort;
import com.example.delivery.domain.DeliveryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@UseCase
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = false)
public class UpdateDeliveryService implements UpdateDeliveryUseCase {

    private final UpdateDeliveryPort updateDeliveryPort;

    @Override
    public DeliveryVo updateDelivery(UpdateDeliveryCommand command) {
        DeliveryVo deliveryVo = DeliveryVo.createGenerateDeliveryVo(
                new DeliveryVo.DeliveryId(command.getDeliveryId()),
                new DeliveryVo.DeliverySizeId(null),
                new DeliveryVo.DeliveryUserId(null),
                new DeliveryVo.DeliveryOrderId(null),
                new DeliveryVo.DeliveryAddress(command.getAddress()),
                new DeliveryVo.DeliveryStatus(command.getStatus()),
                new DeliveryVo.DeliveryCreateAt(null),
                new DeliveryVo.DeliveryUpdateAt(LocalDate.now())
        );

        updateDeliveryPort.updateDelivery(deliveryVo);

        return null;
    }
}
