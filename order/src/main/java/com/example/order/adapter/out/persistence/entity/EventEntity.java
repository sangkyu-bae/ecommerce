package com.example.order.adapter.out.persistence.entity;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter @EqualsAndHashCode(of = "id")
@Table(name = "tb_event") @Builder
@RequiredArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(columnDefinition = "JSON")
    @Type(type = "json")
    Map<String,Object> eventData;

    public void updateStatus(EventStatus eventStatus){
        this.eventStatus = eventStatus;
    }


}
