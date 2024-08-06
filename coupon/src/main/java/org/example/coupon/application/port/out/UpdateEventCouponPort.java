package org.example.coupon.application.port.out;

import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;

import java.util.Queue;

public interface UpdateEventCouponPort {

    void updateQueueEventCoupon(Event.EventId eventId, CouponComponent.CouponComponentUserId userId);

    Queue<Long> findEventQueue(long eventId);

    void removeQueue(long eventId, long userId);
}
