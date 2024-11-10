package com.example.batch.adatptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventCouponScheduled {
    private final StringRedisTemplate redisTemplate;

    private final CouponService service;

    @Value("${event.coupon}")
    private String eventKey;

    @Scheduled(fixedRate = 2000)
    @Transactional
    public void registerEventCoupon(){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        List<Map<String,Object>> eventMapList = service.findEventCouponList();


        if(eventMapList == null || eventMapList.size() < 1 ){
            log.info("존재하는 이벤트가 없습니다.");
            return;
        }



        for(Map<String,Object> req :eventMapList){
            log.info("eventId : {} " , req.get("eventId"));
            log.info("quantity : {}", req.get("quantity"));
            String eventId = String.valueOf(req.get("eventId"));
            String key = eventKey + "-" + eventId;

            Long size = zSetOperations.size(key);
            long end = 400;

            if(size < 400){
                end = size;
            }

            Set<String> rangeData = zSetOperations.range(key, 0, end);
            log.info("end : {}", end);

            if(rangeData == null || rangeData.size() < 1){
                log.info("이벤트에 대기하는 유저가 존재하지 않습니다");
                return;
            }

            List<Map<String,Object>> reqList = new ArrayList<>();
            for(String eventUserId : rangeData){
                Map<String,Object> reqMap = new HashMap<>();

                log.info("userID : {}",eventUserId);
                reqMap.put("userId", Long.parseLong(eventUserId) );
                reqMap.put("status", 0);
                reqMap.put("couponId",3);

                reqList.add(reqMap);
            }

            try{
                service.bulkInsertCouponComponent(reqList);

                long updateQuantity = Long.valueOf((Integer) req.get("quantity")) - end;
                service.updateEventCouponQuantity((Long) req.get("eventId"),updateQuantity);
                service.removeEventCouponByRedis(key,end);
            }catch (Exception e){
                log.info("error");
                e.printStackTrace();
            }
        }
   }
}
