package com.example.order.event;

import com.example.order.adapter.out.persistence.entity.EventStatus;
import com.example.order.adapter.out.persistence.entity.EventType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.SelfValidating;

@Builder
@Getter @EqualsAndHashCode(callSuper = true)
public class EventCommand<T> extends SelfValidating<EventCommand> {
    private final EventStatus eventStatus;

    private final EventType eventType;
    private final T eventData;

    public EventCommand(EventStatus eventStatus, EventType eventType,T eventData){
        this.eventStatus = eventStatus;
        this.eventType = eventType;
        this.eventData = eventData;
    }

}
