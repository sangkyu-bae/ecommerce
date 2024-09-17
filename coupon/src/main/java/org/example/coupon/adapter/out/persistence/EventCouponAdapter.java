package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.adapter.out.persistence.repository.EventRepository;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.error.ErrorException;
import org.example.coupon.infra.error.EventErrorCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class EventCouponAdapter implements FindEventPort {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;
    @Override
    public List<EventEntity> findByStartAtAfter(Event.EventStartAt startAt) {
        List<EventEntity> eventEntityList = eventRepository.findByStartAtBefore(startAt.getStartAt());

        return eventEntityList;
    }

    @Override
    public List<EventEntity> findByStartAtBeforeAndEndAtGreaterThanEqual(LocalDateTime startAt, LocalDateTime endAt) {
        List<EventEntity> eventEntityList = eventRepository.findByStartAtBeforeAndEndAtGreaterThanEqual(startAt,endAt);

        return eventEntityList;
    }

    @Override
    public EventEntity findByEventId(Event.EventId eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId.getId())
                .orElseThrow(()->new ErrorException(EventErrorCode.EVENT_COUPON_NOT_FOUND,"findByEvent"));
        return eventEntity;
    }
}
