package com.example.adminservice.adapter.in.kafka;

import com.example.adminservice.application.port.in.usecase.UpdateProductUseCase;
import com.example.adminservice.application.port.in.command.OrderToUpdateProductCommand;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.properties.AppProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.order.RemoveOrderTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RemoveOrderWithProductTaskConsumer {

    private final ObjectMapper objectMapper;

    private final UpdateProductUseCase updateProductUseCase;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final AppProperties appProperties;
    @KafkaListener(topics ="${kafka.order.remove.topic}",groupId = "${kafka.order.remove.group}")
    public void removeOrderProductTaskListener(String orderRemoveMessage) throws JsonProcessingException {
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
            log.error("error removeOrderProductTaskListener {}" ,e);
            kafkaTemplate.send(appProperties.getRollbackRemoveOrderTopic(),orderRemoveMessage);
        }
    }
}
