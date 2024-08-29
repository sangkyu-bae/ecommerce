package org.example.coupon.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerConfig {

    @Value("${app.common.bootstrap.server}")
    String commonBootstrapServer;
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        log.info("=============================");
        log.info("bootstarpServer : " + commonBootstrapServer);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,commonBootstrapServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
//        return null;
    }

//    private final KafkaProducer<String, String> producer;
//    private final String topic;
//    public KafkaProducerConfig() {
//
//
//        // Producer Initialization ';'
//        Properties props = new Properties();
//
//        // kafka:29092
//        props.put("bootstrap.servers", "!@34536");
//
//        // "key:value"
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        this.producer = new KafkaProducer<>(props);
//        this.topic = "sdkjsdfkj";
//    }
//
//    // Kafka Cluster [key, value] Produce
//    public void sendMessage(String key, String value) {
//        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
//        producer.send(record, (metadata, exception) -> {
//            if (exception == null) {
//                // System.out.println("Message sent successfully. Offset: " + metadata.offset());
//            } else {
//                exception.printStackTrace();
//                // System.err.println("Failed to send message: " + exception.getMessage());
//            }
//        });
//    }
}
