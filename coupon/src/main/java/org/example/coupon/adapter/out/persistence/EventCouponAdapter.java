package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.adapter.out.persistence.repository.EventRepository;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.domain.Event;

import java.util.List;

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
}
