package com.example.delivery.application.port.out;

import com.example.delivery.application.port.in.command.RequestRollbackDeliveryCommand;

public interface RequestRollbackPort {
    void rollBackDelivery(RequestRollbackDeliveryCommand command);
}
