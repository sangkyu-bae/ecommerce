package com.example.batch.adatptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventCouponScheduled {
    private final StringRedisTemplate redisTemplate;

    private final JdbcTemplate jdbcTemplate;

    private final CouponService service;

    @Scheduled(fixedRate = 50000)
    @Transactional
    public void registerEventCoupon(){

        String key = "event.coupon-Event.EventId(id=3)";
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        log.info("size : {}", zSetOperations.size(key));
        Set<String> rangeData = zSetOperations.range(key, 0, 400);


        List<Map<String,Object>> reqList = new ArrayList<>();

        for (String data : rangeData){
            log.info("data : {}",data);

            Map<String,Object> reqMap = new HashMap<>();

            reqMap.put("userId", Long.parseLong(data) );
            reqMap.put("status", 0);
            reqMap.put("couponId",3);


            reqList.add(reqMap);
        }
        log.info("?!!");


        try{
            service.bulkInsertCouponComponent(reqList);
            service.removeEventCouponByRedis(key);
        }catch (Exception e){
            log.info("error");
            e.printStackTrace();
        }

   }


//    @Transactional
//    public void bulkInsertCouponComponent(List<Map<String, Object>> reqList) throws Exception {
//        log.info("????>>>>>>>>>");
//        jdbcTemplate.batchUpdate(
//                "INSERT INTO tb_coupon_component (user_id, status, coupon_id) VALUES (?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        Map<String,Object> req = reqList.get(i);
//                        ps.setLong(1, (Long) req.get("userId"));
//                        ps.setInt(2, (Integer) req.get("status")); // Assuming status is an enum, convert to string
////                        ps.setTimestamp(3, Timestamp.valueOf(couponComponent.getEndAt())); // Convert LocalDateTime to Timestamp
//                        ps.setLong(3, (Integer) req.get("couponId")); // Assuming fetchCoupon has an ID field
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        log.info(String.valueOf(reqList.size()));
//                        return reqList.size();
//                    }
//                }
//        );
//
//        throw new RuntimeException("db insert failed");
//    }

    private List<String> getAllSortedSetKeys() {
        Set<String> allKeys = new HashSet<>();
        ScanOptions scanOptions = ScanOptions.scanOptions().match("event.coupon").build();

        // SCAN 명령어를 사용하여 모든 키 검색
        redisTemplate.execute((RedisCallback<Object>) (redisConnection) -> {
            // Redis의 SCAN 명령어 실행
            redisConnection.scan(scanOptions).forEachRemaining(key -> {
                allKeys.add(new String(key));
            });
            return null;
        });

        return new ArrayList<>(allKeys);
    }


}
