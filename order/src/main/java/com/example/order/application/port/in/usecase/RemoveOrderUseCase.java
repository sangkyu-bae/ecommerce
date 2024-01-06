package com.example.order.application.port.in.usecase;

import com.example.order.application.port.in.command.FailRemoveOrderCommand;
import com.example.order.application.port.in.command.RemoveOrderCommand;

public interface RemoveOrderUseCase {
    void removeOrder(RemoveOrderCommand command);

    void failRemoveOrder(FailRemoveOrderCommand command);

    void removeOrderByEvent(RemoveOrderCommand command);
}
