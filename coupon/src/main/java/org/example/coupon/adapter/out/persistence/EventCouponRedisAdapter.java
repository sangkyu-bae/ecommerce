package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;
import org.example.coupon.application.port.out.UpdateEventCouponPort;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class EventCouponRedisAdapter implements UpdateEventCouponPort {

    @Value("${event.coupon}")
    private String EVENT_COUPON_KEY;

    private final StringRedisTemplate redisTemplate;

    @Override
    public void updateQueueEventCoupon(Event.EventId eventId,
                                       CouponComponent.CouponComponentUserId userId) {
        ZSetOperations zSetOps =redisTemplate.opsForZSet();

        String key = EVENT_COUPON_KEY + "-" +String.valueOf(eventId.getId());
        String rankKey = String.valueOf(userId);

        if(hasKey(key,rankKey)){
            log.info("Key already exists: {}", rankKey);
            return;
        }
        double score = System.currentTimeMillis() / 1000.0;

        zSetOps.add(key,rankKey,score);
        log.info("Key added: {}, time: {}", rankKey, score);

    }

    @Override
    public Queue<Long> findEventQueue(long eventId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        String key = EVENT_COUPON_KEY + "-" + String.valueOf(eventId);

        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = zSetOps.rangeWithScores(key, 0, -1);
        Queue <Long> queue = new LinkedList<>();
        if (rangeWithScores != null) {
            for (ZSetOperations.TypedTuple<String> tuple : rangeWithScores) {
                String userId = tuple.getValue();
                queue.add(Long.valueOf(userId));
            }
        }
        return queue;
    }



    @Override
    public void removeQueue(long eventId, long userId){
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        String key = EVENT_COUPON_KEY + "-" +String.valueOf(eventId);
        String rankKey = String.valueOf(userId);

        Long removedCount = zSetOps.remove(key, rankKey);

        /**
         * todo : 삭제 오류시 에러 호출
         * */
        if (removedCount != null && removedCount > 0) {
            log.info("쿠폰 발급완료 : {} sotredSet에서 {} : 유저 삭제합니다 ", rankKey, eventId);
        } else {

        }
    }

    @Override
    public void addEventQueue(Event.EventId eventId, long userId) {
        ZSetOperations zSetOps =redisTemplate.opsForZSet();

        String key = EVENT_COUPON_KEY + "-" +String.valueOf(eventId.getId());
        String rankKey = String.valueOf(userId);

        if(hasKey(key,rankKey)){
//            log.info("Key already exists: {}", rankKey);
            log.info("{} Key already exists: {}", key, rankKey);
            return;
        }
        double score = System.currentTimeMillis() / 1000.0;

        zSetOps.add(key,rankKey,score);
        log.info("key : {} ,Key added: {}, time: {}",key, rankKey, score);
    }

    @Override
    public void refreshQueue(long eventId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        String key = EVENT_COUPON_KEY + "-" + String.valueOf(eventId);

        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = zSetOps.rangeWithScores(key, 0, -1);
        Queue <Long> queue = new LinkedList<>();
        if (rangeWithScores != null) {
            for (ZSetOperations.TypedTuple<String> tuple : rangeWithScores) {
                String userId = tuple.getValue();
                log.info("sotredSet에서 {} : 유저 삭제합니다 ", userId, eventId);
                zSetOps.remove(key, tuple.getValue());
            }
        }
    }


    private boolean hasKey(String key,String rankKey){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        // setKey가 존재하는지 확인
        Double existingScore = zSetOps.score(key, rankKey);
        if (existingScore != null) {
            return true;
        }

        return false;
    }
}


