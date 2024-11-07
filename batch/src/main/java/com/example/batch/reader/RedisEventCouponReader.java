package com.example.batch.reader;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class RedisEventCouponReader implements ItemReader<String> {

    private final StringRedisTemplate redisTemplate;

//    private String key;
    private int start;
    private int end;
    private int currentIndex = 0;
    private List<String> data;

    @Value("${event.coupon}")
    private String EVENT_COUPON_KEY;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        Set<String> keyStrings = getAllSortedSetKeys();
        if (data == null) {
            loadRangeData(getAllSortedSetKeys().get(0));
        }

        if (currentIndex < data.size()) {
            return data.get(currentIndex++);
        } else {
            return null;
        }

    }

    public List<String> getAllSortedSetKeys() {
        Set<String> allKeys = new HashSet<>();
        ScanOptions scanOptions = ScanOptions.scanOptions().match("EVENT_COUPON_KEY*").build();

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

    private void loadRangeData(String key) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> rangeData = zSetOperations.range(key, start, end);
        if (rangeData != null) {
            data = new ArrayList<>(rangeData);
        } else {
            data = Collections.emptyList();
        }
    }
}
