package org.example.adapter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.application.RegisterCouponCommand;
import org.example.dto.RegisterEventCoupon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class RegisterCouponProducer {

    private final KafkaTemplate <String,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.proxy.create.topic}")
    private String SEND_CREATE_COUPON_TOPIC;


    public void send(RegisterEventCoupon eventCoupon){
        try{
            String inputProductIdJson = objectMapper.writeValueAsString(eventCoupon);
            kafkaTemplate.send(SEND_CREATE_COUPON_TOPIC,inputProductIdJson);
            log.info("register event coupon");
        }catch (JsonProcessingException e){
            log.error("fail send RegisterCouponProducer : {}" ,e);
        }
    }
}
