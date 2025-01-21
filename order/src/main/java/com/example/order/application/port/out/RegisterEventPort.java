package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.domain.Event;

public interface RegisterEventPort {
    EventEntity registerEvent(Event event,String orderAggregateIdentifier);
}
