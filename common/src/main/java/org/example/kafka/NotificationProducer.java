package org.example.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import infra.KafkaProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.aop.NotificationClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {

    private final ObjectMapper objectMapper;

    @Value("${kafka.notification.topic}")
    private String SEND_NOTIFICATION_TOPIC;
    @Value("${app.common.bootstrap.server}")
    String commonBootstrapServer;

    private final KafkaTemplate<String,String> kafkaTemplate;



    public void sendCreateNotification(NotificationClient notificationClient){
        try{
            String inputJsonString = objectMapper.writeValueAsString(notificationClient);
            log.info(inputJsonString);
            kafkaTemplate.send(SEND_NOTIFICATION_TOPIC, String.valueOf(notificationClient.getFormMember()),inputJsonString);
        }catch (JsonProcessingException e){
            log.error("fail send notification service : {}" , e);
        }
    }
}
