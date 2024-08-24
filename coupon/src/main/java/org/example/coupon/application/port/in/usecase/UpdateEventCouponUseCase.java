package org.example.coupon.application.port.in.usecase;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;

import java.util.Queue;

public interface UpdateEventCouponUseCase {
    boolean decreaseEventCoupon(String lockName, CouponIssuanceCommand command);

    boolean isQueueEventCoupon(UpdateEventCouponCommand command);

    void process();

    void addEventQueue(UpdateEventCouponCommand command);

    void queueProcess(Queue<Long> queue, EventEntity event, CouponEntity couponEntity);
}
