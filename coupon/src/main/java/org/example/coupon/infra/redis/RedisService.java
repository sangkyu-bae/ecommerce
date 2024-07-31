package org.example.coupon.infra.redis;


import io.lettuce.core.RedisClient;
import lombok.RequiredArgsConstructor;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedissonClient redissonClient;

    @DistributedLock(key ="#lockName")
    public void lock(String key){

    }
}
