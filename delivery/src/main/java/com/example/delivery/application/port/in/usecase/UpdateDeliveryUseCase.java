package com.example.delivery.application.port.in.usecase;

import com.example.delivery.application.port.in.command.UpdateDeliveryCommand;
import com.example.delivery.domain.DeliveryVo;

public interface UpdateDeliveryUseCase {

    DeliveryVo updateDelivery(UpdateDeliveryCommand command);
}
