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

    private final DistributedLock distributedLock;

    public void lock(String key){
        RLock lock = redissonClient.getLock(key);
        try {
            boolean available = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());

            if (!available) {
                //Lock 획득 실패
                return;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
