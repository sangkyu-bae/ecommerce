package com.example.delivery.application.port.in.usecase;

import com.example.delivery.application.port.in.command.RequestRollbackDeliveryCommand;

public interface RollbackDeliveryEventUseCase {
    void RollbackEventSend(RequestRollbackDeliveryCommand command);
}
