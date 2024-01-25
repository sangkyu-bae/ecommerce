package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.domain.Event;

public interface RegisterEventPort {
    EventEntity registerEvent(Event event);
}
