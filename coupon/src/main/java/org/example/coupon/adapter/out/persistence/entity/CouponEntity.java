package org.example.coupon.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_coupon") @Builder
public class CouponEntity {

    @Id @GeneratedValue
    private Long id;

    private Long createAdminId;

    private int salePercent;

    private String name;

    private LocalDateTime createAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "coupon")
    private List<CouponComponentEntity> couponComponents;
}
