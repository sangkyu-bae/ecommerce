package org.example.coupon.adapter.out.persistence;

import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.domain.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event mapToDomain(EventEntity eventEntity,boolean isIssued){
        return Event.createGenerateEvent(
                new Event.EventId(eventEntity.getId()),
                new Event.EventCouponName(eventEntity.getCouponName()),
                new Event.EventSalePercent(eventEntity.getSalePercent()),
                new Event.EventQuantity(eventEntity.getQuantity()),
                new Event.EventStartAt(eventEntity.getStartAt()),
                new Event.EventEndAt(eventEntity.getEndAt()),
                new Event.EventIssued(isIssued)
        );
    }
}
