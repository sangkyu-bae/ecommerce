package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.adapter.out.persistence.repository.EventRepository;
import org.example.coupon.application.port.out.RegisterEventPort;
import org.example.coupon.domain.Event;

@PersistenceAdapter
@RequiredArgsConstructor
public class EventPersistenceAdapter implements RegisterEventPort {

    private final EventRepository eventRepository;
    @Override
    public EventEntity registerEvent(Event event) {

        EventEntity registerEventEntity = EventEntity.builder()
                .salePercent(event.getSalePercent())
                .couponName(event.getCouponName())
                .quantity(event.getQuantity())
                .startAt(event.getStartAt())
                .endAt(event.getEndAt())
                .build();

        return eventRepository.save(registerEventEntity);
    }
}
