package org.example.coupon.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CouponProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.coupon.create.topic}")
    private String SEND_CREATE_COUPON_TOPIC;



    public void sendCreateCoupon(long userId,long eventId){
        try{
            String inputCouponJson = objectMapper.writeValueAsString(new CouponTest(userId,eventId));
            kafkaTemplate.send(SEND_CREATE_COUPON_TOPIC,inputCouponJson);
            log.info("coupon send");
        }catch (JsonProcessingException e){
            log.error("fail send ranking service : {}",e);
        }
    }

    @Getter
    static class CouponTest{
        long userId;
        long eventId;

        CouponTest(long userId, long eventId){
            this.userId = userId;
            this.eventId = eventId;
        }
    }
}
