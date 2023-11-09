package com.example.order.adapter.out.kafka;

import com.example.order.application.port.out.SendCreateOrderTaskPort;
import org.example.task.OrderTask;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class TaskProducer implements SendCreateOrderTaskPort {

    @Override
    public void sendCreateOrderTask(OrderTask task) {

    }

}
