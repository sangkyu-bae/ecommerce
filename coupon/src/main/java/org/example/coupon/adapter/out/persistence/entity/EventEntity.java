package org.example.coupon.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_event") @Builder
public class EventEntity {

    @Id @GeneratedValue
    private Long id;

    private String couponName;

    private int salePercent;

    private int quantity;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}
