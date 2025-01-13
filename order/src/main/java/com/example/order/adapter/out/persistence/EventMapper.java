package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    public Event mapToDomainEntity(EventEntity eventEntity){

        Event event = Event.createGenerate(
                eventEntity.getId(),
                eventEntity.getEventStatus(),
                eventEntity.getEventType(),
                eventEntity.getEventData()
        );

        return event;
    }
}
