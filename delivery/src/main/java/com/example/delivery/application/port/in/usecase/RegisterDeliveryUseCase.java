package com.example.delivery.application.port.in.usecase;

import com.example.delivery.application.port.in.command.RegisterDeliveryCommand;
import com.example.delivery.domain.DeliveryVo;

import java.util.List;

public interface RegisterDeliveryUseCase {

    DeliveryVo registerDelivery(RegisterDeliveryCommand command);

    List<DeliveryVo> bulkRegisterDelivery(List<RegisterDeliveryCommand> commands);
}
