package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.CouponComponentEntity;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.domain.Event;

public interface UpdateEventPort {
    EventEntity decreaseEventCoupon(Event.EventId eventId);

    void sendNotification(long memberId, String eventName, String notification, int type,int sseType);

    boolean redeemEvent(Event.EventId eventId);

}
