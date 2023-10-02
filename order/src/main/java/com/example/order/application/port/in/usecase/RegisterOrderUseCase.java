package com.example.order.application.port.in.usecase;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.module.domain.order.orderentity.OrderVo;

public interface RegisterOrderUseCase {

    OrderVo registerOrder(RegisterOrderCommand command);
}
