package org.example.adapter.web;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.WebAdapter;
import org.example.application.CouponProxyService;
import org.example.application.RegisterCouponCommand;
import org.example.dto.RegisterEventCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;
import java.util.Random;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class CouponProxyController {


    private final CouponProxyService couponProxyService;

    private static final Logger logger = LoggerFactory.getLogger("elk");

    @GetMapping("/coupon-proxy/log")
    public ResponseEntity<String> log(){
        int b = 1;
        logger.info("log back to kafka : {}",b);

        int [] a = new int[2];

        try{
            int c = a[3];
        }catch (Exception e){
            logger.error("error {}, info {}",e.getStackTrace(), e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println("??");
            System.out.println("??");
            e.printStackTrace();
        }

        logs();

        return ResponseEntity.ok().body("ok");
    }

    private void logs(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>("kafka-elk-test-log", "테스트 메시지"));
        producer.close();

        System.out.println("Kafka 메시지 전송 완료");
    }

    @PostMapping("/coupon-proxy/v2/{eventId}")
    public ResponseEntity<String> proxyCoupon(@PathVariable("eventId") long eventId,
                                              @RequestHeader("X-User-Id") long userId){
        RegisterCouponCommand command = RegisterCouponCommand.builder()
                .eventId(eventId)
                .userId(userId)
                .build();


        String res = couponProxyService.execute(command);

        if(res.equals("fail")){
            return ResponseEntity.status(405).body(res);
        }

        return  ResponseEntity.ok().body(res);
    }

    @PostMapping("/coupon-proxy/test/{eventId}")
    public ResponseEntity<String> test(@PathVariable("eventId") long eventId){

//        for(int i = 1; i <= 500; i++){
//            RegisterCouponCommand command = RegisterCouponCommand.builder()
//                    .eventId(eventId)
//                    .userId(i)
//                    .build();
//
//            couponProxyService.execute(command);
//        }

        Random random = new Random();
        long randomUserId = 1 + (long)(random.nextDouble() * (100000)); // 1부터 100000까지
        logger.info("user Id : {}" );

        RegisterCouponCommand command = RegisterCouponCommand.builder()
                    .eventId(eventId)
                    .userId(randomUserId)
                    .build();

        couponProxyService.execute(command);

        return ResponseEntity.ok().body("ok");
    }



}
