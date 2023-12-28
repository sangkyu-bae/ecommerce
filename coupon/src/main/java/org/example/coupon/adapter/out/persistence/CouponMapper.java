package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.CouponComponentVo;
import org.example.coupon.domain.CouponVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CouponMapper {

    public CouponVo mapToDomainEntity(CouponEntity coupon){

        CouponVo couponVo = CouponVo.createGenerateCouponVo(
                new CouponVo.CouponId(coupon.getId()),
                new CouponVo.CouponCreateAdminId(coupon.getCreateAdminId()),
                new CouponVo.CouponSalePercent(coupon.getSalePercent()),
                new CouponVo.CouponName(coupon.getName()),
                new CouponVo.CouponCreateAt(coupon.getCreateAt()),
                null
        );

        List<CouponComponentVo> couponComponentVoList = coupon.getCouponComponents().stream()
                .map(couponComponentEntity -> CouponComponentVo.createGenerateCouponComponentVo(
                        new CouponComponentVo.CouponComponentId(couponComponentEntity.getId()),
                        new CouponComponentVo.CouponComponentUserId(couponComponentEntity.getUserId()),
                        CouponComponentVo.CouponStatusCode.findStatusCode(couponComponentEntity.getStatus()),
                        new CouponComponentVo.CouponComponentEndAt(couponComponentEntity.getEndAt()),
                        couponVo
                )).collect(Collectors.toList());

        couponVo.setCouponComponentVoList(couponComponentVoList);

        return couponVo;
    }
}
