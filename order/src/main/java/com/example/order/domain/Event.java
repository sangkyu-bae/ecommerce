package com.example.order.domain;

import com.example.order.adapter.out.persistence.entity.EventStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Event <T>{

    private final Long id;


    private final EventStatus eventStatus;

    private final T eventData;

    public static <T> Event<T> createGenerate(Long id, EventStatus eventStatus, T eventData) {
        return new Event<>(id, eventStatus, eventData);
    }
}
