package com.example.order.domain;

import com.example.order.adapter.out.persistence.entity.EventStatus;
import com.example.order.adapter.out.persistence.entity.EventType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Event {

    private final String id;

    private final EventStatus eventStatus;

    private final EventType eventType;

    private final Map<String,Object> eventData;

    public static  Event createGenerate(String id, EventStatus eventStatus, EventType eventType,Map<String,Object> eventData) {
        return new Event(id, eventStatus, eventType,eventData);
    }
}
