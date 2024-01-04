package com.example.order.adapter.out.kafka;

import com.example.order.adapter.out.external.delivery.DeliveryEvent;
import com.example.order.adapter.out.external.delivery.OrderInfoRequest;
import com.example.order.application.port.out.SendCreateDeliveryEventPort;
import com.example.order.application.port.out.SendCreateOrderTaskPort;
import com.example.order.application.port.out.SendRemoveOrderTaskPort;
import com.example.order.infra.properties.AppProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.OrderTask;
import org.example.task.order.RemoveOrderTask;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskProducer implements SendCreateOrderTaskPort, SendRemoveOrderTaskPort, SendCreateDeliveryEventPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AppProperties appProperties;
    private final ObjectMapper objectMapper;
    @Override
    public void sendCreateOrderTask(OrderTask task) {
        try {
            String inputTask = objectMapper.writeValueAsString(task);
            kafkaTemplate.send(appProperties.getOrderTopic(),inputTask);
        } catch (JsonProcessingException e) {
            log.error("sendCreateOrderTask Error message = {}", task, e);
        }
    }

    @Override
    public void removeOrderTask(RemoveOrderTask task) {
        try{
            String inputTask = objectMapper.writeValueAsString(task);
            // kafka 설정 필요
            kafkaTemplate.send(appProperties.getSendToRemoveOrder(),inputTask);
        } catch (JsonProcessingException e) {
            log.error("removeOrderTask Error message = {}, e = {}", task, e);
        }
    }

    @Override
    public void createDeliveryEvent(DeliveryEvent event) {
        try {
            String inputTask = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(appProperties.getCreateDelivery(),inputTask);
        } catch (JsonProcessingException e) {
            log.error("createDeliveryEvent Error message = {} , {}", event, e);
        }
    }
}
