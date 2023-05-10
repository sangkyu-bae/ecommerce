package com.example.demo.infra.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String,Object> redisTemplate;

    public void saveTokenToRedis(String userId, String tokenValue, Long expiredTime) {
        redisTemplate.opsForValue().set(userId, tokenValue, expiredTime, TimeUnit.MILLISECONDS);
    }
}
