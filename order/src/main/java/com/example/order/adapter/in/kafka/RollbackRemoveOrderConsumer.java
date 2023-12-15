package com.example.order.adapter.in.kafka;

import com.example.order.application.port.in.command.FailRemoveOrderCommand;
import com.example.order.application.port.in.usecase.RemoveOrderUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.CountDownLatchManager;
import org.example.task.OrderSubTask;
import org.example.task.OrderTask;
import org.example.task.order.RemoveOrderTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RollbackRemoveOrderConsumer {

    private final ObjectMapper objectMapper;

    private final RemoveOrderUseCase removeOrderUseCase;
    @KafkaListener(topics ="${kafka.order.remove.rollback.topic}",groupId = "${kafka.order.remove.rollback.group}")
    public void rollbackRemoveOrderTaskListener(String orderRemoveMessage){
        RemoveOrderTask task = null;
        try{
            task = objectMapper.readValue(orderRemoveMessage, RemoveOrderTask.class);
            FailRemoveOrderCommand command = FailRemoveOrderCommand.builder()
                    .orderId(task.getOrderId())
                    .build();
            removeOrderUseCase.failRemoveOrder(command);
        }catch (Exception e){
            log.error("rollbackRemoveOrderTaskListener Error message = {}",orderRemoveMessage,e);
        }
    }
}
