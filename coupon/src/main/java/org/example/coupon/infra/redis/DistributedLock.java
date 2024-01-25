package org.example.coupon.infra.redis;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {

    /**
     * 락의 시간 단위
     */
    default TimeUnit timeUnit() {
        return TimeUnit.SECONDS;
    }

    /**
     * 락을 기다리는 시간 (default - 5s)
     * 락 획득을 위해 waitTime 만큼 대기한다
     */
    default long waitTime() {
        return 5L;
    }

    /**
     * 락 임대 시간 (default - 3s)
     * 락을 획득한 이후 leaseTime 이 지나면 락을 해제한다
     */
    default long leaseTime() {
        return 3L;
    }
}