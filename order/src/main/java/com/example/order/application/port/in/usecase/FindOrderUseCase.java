package com.example.order.application.port.in.usecase;

import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.module.domain.order.orderentity.OrderVo;

public interface FindOrderUseCase {

    OrderVo findOrder(FindOrderCommand command);
}
