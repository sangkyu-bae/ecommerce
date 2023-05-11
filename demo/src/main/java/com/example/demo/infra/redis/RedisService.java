package com.example.demo.infra.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class RedisService {
    private final RedisTemplate<String,Object> redisTemplate;


    public void saveTokenToRedis(String userId, String tokenValue, Long expiredTime) {
        redisTemplate.opsForValue().set(userId, tokenValue, expiredTime, TimeUnit.MILLISECONDS);
    }

    public String getValues(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
