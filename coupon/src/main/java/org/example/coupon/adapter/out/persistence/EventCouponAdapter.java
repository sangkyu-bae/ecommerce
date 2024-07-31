package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.adapter.out.persistence.repository.EventRepository;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.domain.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class EventCouponAdapter implements FindEventPort {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;
    @Override
    public List<EventEntity> findByStartAtAfterAndEndAtBefore(Event.EventStartAt startAt, Event.EventEndAt endAt) {
        List<EventEntity> eventEntityList = eventRepository.findByStartAtAfterAndEndAtBefore(startAt.getStartAt(),endAt.getEndAt());

        return eventEntityList;
    }
}
