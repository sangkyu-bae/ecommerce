package com.example.delivery.application.service;

import com.example.delivery.application.port.in.command.RequestRollbackDeliveryCommand;
import com.example.delivery.application.port.in.usecase.RollbackDeliveryEventUseCase;
import com.example.delivery.application.port.out.RequestRollbackPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RollbackDeliveryService implements RollbackDeliveryEventUseCase {

    private RequestRollbackPort requestRollbackPort;
    @Override
    public void RollbackEventSend(RequestRollbackDeliveryCommand command) {
        requestRollbackPort.rollBackDelivery(command);
    }
}
