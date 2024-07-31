package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface FindEventPort {

    List<EventEntity> findByStartAtAfterAndEndAtBefore(Event.EventStartAt startAt, Event.EventEndAt endAt);

}
