package com.example.order.adapter.out.persistence.entity;


import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter @EqualsAndHashCode(of = "id")
@Table(name = "tb_event") @Builder
@RequiredArgsConstructor @AllArgsConstructor
@ToString
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
public class EventEntity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(columnDefinition = "JSON")
    @Type(type = "json")
    Map<String,Object> eventData;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public void updateStatus(EventStatus eventStatus){
        this.eventStatus = eventStatus;
    }

    public void updateAt(LocalDateTime updateAt){
        this.updateAt = updateAt;
    }

}
