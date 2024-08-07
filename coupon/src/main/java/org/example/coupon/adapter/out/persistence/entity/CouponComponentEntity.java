package org.example.coupon.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_coupon_component") @Builder
public class CouponComponentEntity {

    @Id @GeneratedValue
    private Long id;

    private long userId;

    private int status;

    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private CouponEntity coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventEntity event;

}
