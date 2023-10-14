package com.example.delivery.application.port.in.usecase;

import com.example.delivery.application.port.in.command.RegisterDeliveryCommand;
import com.example.delivery.domain.delivery.DeliveryVo;

public interface RegisterDeliveryUseCase {

    DeliveryVo registerDelivery(RegisterDeliveryCommand command);
}
