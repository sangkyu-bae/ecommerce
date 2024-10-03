package org.example.coupon.infra.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * AOP에서 트랜잭션 분리를 위한 클래스
 */
@Component
@Slf4j
public class AopForTransaction {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>>>>joinPoint");
        return joinPoint.proceed();
    }

}
