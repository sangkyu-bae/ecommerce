package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.adapter.out.persistence.entity.EventStatus;

public interface UpdateEventPort {
    EventEntity updateEvent(EventStatus eventStatus);
}
