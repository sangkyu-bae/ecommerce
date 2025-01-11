package com.example.order.adapter.out.external.event;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.adapter.out.persistence.entity.EventStatus;
import com.example.order.adapter.out.persistence.repository.EventEntityRepository;
import com.example.order.application.port.out.RegisterEventPort;
import com.example.order.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class EventAdaptor implements RegisterEventPort {

    private final EventEntityRepository eventEntityRepository;
    @Override
    public EventEntity registerEvent(Event event) {


        return null;
    }
}
