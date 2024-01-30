package org.example.coupon.adapter.out.persistence;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Coupon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CouponMapper {

    public Coupon mapToDomainEntity(CouponEntity coupon){

        Coupon couponVo = Coupon.createGenerateCoupon(
                new Coupon.CouponId(coupon.getId()),
                new Coupon.CouponCreateAdminId(coupon.getCreateAdminId()),
                new Coupon.CouponSalePercent(coupon.getSalePercent()),
                new Coupon.CouponName(coupon.getName()),
                new Coupon.CouponCreateAt(coupon.getCreateAt()),
                null,
                new Coupon.CouponAggregateIdentifier(coupon.getAggregateIdentifier())
        );

        List<CouponComponent> couponComponentVoList = coupon.getCouponComponents().stream()
                .map(couponComponentEntity -> CouponComponent.createGenerateCouponComponentVo(
                        new CouponComponent.CouponComponentId(couponComponentEntity.getId()),
                        new CouponComponent.CouponComponentUserId(couponComponentEntity.getUserId()),
                        CouponComponent.CouponStatusCode.findStatusCode(couponComponentEntity.getStatus()),
                        new CouponComponent.CouponComponentEndAt(couponComponentEntity.getEndAt()),
                        null
                )).collect(Collectors.toList());

        couponVo.setCouponComponentVoList(couponComponentVoList);

        return couponVo;
    }
}
