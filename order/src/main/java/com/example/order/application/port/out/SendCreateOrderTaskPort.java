package com.example.order.application.port.out;

import org.example.task.OrderTask;

public interface SendCreateOrderTaskPort {

    void sendCreateOrderTask(OrderTask task);
}
