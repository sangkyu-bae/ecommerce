package com.example.order.application.port.out;

import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.application.port.in.command.RegisterOrderCommand;

public interface SendAxonOrderPort {

    void sendOrderWithSaga(RegisterOrderCommand command);
}
