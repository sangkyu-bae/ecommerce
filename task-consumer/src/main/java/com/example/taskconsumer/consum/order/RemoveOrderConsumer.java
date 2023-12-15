package com.example.taskconsumer.consum.order;

import com.example.taskconsumer.config.AppProperties;
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
public class RemoveOrderConsumer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final AppProperties appProperties;

    @KafkaListener(topics = "${kafka.order.remove.topic}", groupId = "${kafka.order.remove.group}")
    public void removeOrderTaskListener(String removeTaskMessage) {
        RemoveOrderTask task = null;
        try {
            task = objectMapper.readValue(removeTaskMessage, RemoveOrderTask.class);
            kafkaTemplate.send(appProperties.getOrderRemoveTopic(),removeTaskMessage);
        } catch (Exception e) {
            log.error("removeOrderTaskListener Error message = {}", removeTaskMessage, e);
        }
    }
}
