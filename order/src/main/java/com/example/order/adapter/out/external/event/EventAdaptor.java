package com.example.order.adapter.out.external.event;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.adapter.out.persistence.entity.EventStatus;
import com.example.order.adapter.out.persistence.repository.EventEntityRepository;
import com.example.order.application.port.out.RegisterEventPort;
import com.example.order.application.port.out.UpdateEventPort;
import com.example.order.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class EventAdaptor implements RegisterEventPort, UpdateEventPort {

    private final EventEntityRepository eventEntityRepository;
    @Override
    public EventEntity registerEvent(Event event,String orderAggregateIdentifier) {

        EventEntity eventEntity = EventEntity.builder()
                .id(orderAggregateIdentifier)
                .eventStatus(event.getEventStatus())
                .eventType(event.getEventType())
                .eventData(event.getEventData())
                .build();


        return eventEntityRepository.save(eventEntity);
    }

    @Override
    @Transactional
    public EventEntity updateEvent(String eventId, EventStatus eventStatus) {

        EventEntity eventEntity = eventEntityRepository.findByIdAndEventStatus(eventId,EventStatus.INIT);

        if(eventEntity == null){
            throw new IllegalArgumentException("");
        }
        eventEntity.updateStatus(eventStatus);

        return eventEntity;
    }
}
