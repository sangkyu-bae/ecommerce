package com.example.demo.adapter.out.kafka;


import com.example.demo.application.port.out.SendRemoveOrderToProductPort;
import com.example.demo.infra.config.AppProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.OrderTask;
import org.example.task.order.RemoveOrderTask;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskProducer implements SendRemoveOrderToProductPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AppProperties appProperties;
    private final ObjectMapper objectMapper;

    @Override
    public void removeOrderTask(RemoveOrderTask task) {
        try{
            String inputTask = objectMapper.writeValueAsString(task);
            // kafka 설정 필요
            kafkaTemplate.send(appProperties.getRemoveOrderTopic(),inputTask);
        } catch (JsonProcessingException e) {
            log.error("removeOrderTask Error message = {}, e = {}", task, e);
        }
    }
}
