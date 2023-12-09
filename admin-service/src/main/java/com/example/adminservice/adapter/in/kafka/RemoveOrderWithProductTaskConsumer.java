package com.example.adminservice.adapter.in.kafka;

import com.example.adminservice.application.port.in.UpdateProductUseCase;
import com.example.adminservice.application.port.in.product.OrderToUpdateProductCommand;
import com.example.adminservice.infra.error.ErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.order.RemoveOrderTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RemoveOrderWithProductTaskConsumer {

    private final ObjectMapper objectMapper;

    private final UpdateProductUseCase updateProductUseCase;
    @KafkaListener(topics ="${kafka.order.remove.topic}",groupId = "${kafka.order.remove.group}")
    public void resultTaskListener(String orderRemoveMessage) throws JsonProcessingException {
        RemoveOrderTask task = null;
        try{
           task = objectMapper.readValue(orderRemoveMessage, RemoveOrderTask.class);
            OrderToUpdateProductCommand command = OrderToUpdateProductCommand.builder()
                    .amount(-task.getAmount())
                    .sizeId(task.getSizeId())
                    .build();

            command.validateSelf();

            updateProductUseCase.updateProductQuantity(command);

        } catch (ErrorException e) {
            //실패 메세지 전송
        }
    }
}
