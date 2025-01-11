package com.example.order.adapter.out.persistence.entity;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter @EqualsAndHashCode(of = "id")
@Table(name = "tb_event") @Builder
@RequiredArgsConstructor
public class EventEntity <T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Column(columnDefinition = "JSON")
    @Type(type = "json")
    T eventData;

}
