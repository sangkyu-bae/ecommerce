package org.example.coupon.adapter.out.persistence.entity;

import lombok.*;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.infra.error.CouponErrorCode;
import org.example.coupon.infra.error.ErrorException;
import org.springframework.transaction.annotation.Transactional;

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

    private String aggregateIdentifier;

    public void checkAndUpdateCoupon(long userId){
        if(couponComponents.isEmpty()){
            throw new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"checkAndUpdateCoupon");
        }

        CouponComponentEntity couponComponentEntity = couponComponents.stream()
                .filter(component -> component.getUserId() == userId).findFirst()
                .orElseThrow(() -> new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"checkAndUpdateCoupon?"));

        if(couponComponentEntity==null){
            throw new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"checkAndUpdateCoupon??");
        }

        couponComponentEntity.setStatus(CouponComponent.CouponStatusCode.USE_SUCCESS.getStatus());
    }

    @Transactional
    public boolean existCouponByUserId(long userId){
        return couponComponents.stream()
                .anyMatch(component -> component.getUserId() == userId);
    }
    public void addCouponComponent(CouponComponentEntity couponComponentEntity){
        this.couponComponents.add(couponComponentEntity);
    }
}
