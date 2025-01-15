package com.example.order.application.port.out;

import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.domain.OrderVo;

public interface SendAxonOrderPort {

    void sendOrderWithSaga(RegisterOrderCommand command, OrderVo.OrderId orderId, String eventId);
}
