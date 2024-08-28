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
//@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaProducer<String, String> producer;

    private final ObjectMapper objectMapper;

//    @Value("${kafka.notification.topic}")
//    private String SEND_NOTIFICATION_TOPIC;
//    @Value("${app.common.bootstrap.server}")
//    String commonBootstrapServer;


//    private final KafkaProducerConfig kafkaProducerConfig;
    private final String topic;



    public NotificationProducer(){
        this.topic = "test";
        Properties props = new Properties();

        // kafka:29092
        props.put("bootstrap.servers", "Test");

        // "key:value"
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
        objectMapper = new ObjectMapper();
    }
    public void sendCreateNotification(NotificationClient notificationClient){
        try{
            String inputJsonString = objectMapper.writeValueAsString(notificationClient);
            log.info(inputJsonString);
            log.error("============info============");
//            log.error("log topic : {}" ,SEND_NOTIFICATION_TOPIC);
//            log.error("log bootstrap : {}" ,commonBootstrapServer);
            log.error("============info============");
//            kafkaTemplate.send(SEND_NOTIFICATION_TOPIC,inputJsonString);
//            kafkaProducerConfig.sendMessage(SEND_NOTIFICATION_TOPIC,inputJsonString);

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "test","Test");
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    // System.out.println("Message sent successfully. Offset: " + metadata.offset());
                } else {
                    exception.printStackTrace();
                    // System.err.println("Failed to send message: " + exception.getMessage());
                }
            });
        }catch (JsonProcessingException e){
            log.error("fail send notification service : {}" , e);
        }
    }


}
