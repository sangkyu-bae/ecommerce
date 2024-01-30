package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.adapter.out.persistence.repository.EventRepository;
import org.example.coupon.application.port.out.RegisterEventPort;
import org.example.coupon.application.port.out.UpdateEventPort;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.error.ErrorException;
import org.example.coupon.infra.error.EventErrorCode;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class EventPersistenceAdapter implements RegisterEventPort, UpdateEventPort {

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

    @Override
    @Transactional(readOnly = false)
    public EventEntity decreaseEventCoupon(Event.EventId eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId.getId())
                .orElseThrow(()->new ErrorException(EventErrorCode.EVENT_COUPON_NOT_FOUND,"decreaseEventCoupon"));

        eventEntity.decreaseQuantity();
        return eventRepository.save(eventEntity);
    }
}
