package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.CouponComponentEntity;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.persistence.repository.CouponEntityRepository;
import org.example.coupon.application.port.out.FindCouponPort;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.domain.CouponComponentVo;
import org.example.coupon.domain.CouponVo;
import org.example.coupon.infra.error.CouponErrorCode;
import org.example.coupon.infra.error.ErrorException;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class CouponPersistenceAdapter implements RegisterCouponPort, FindCouponPort {

    private final CouponEntityRepository couponEntityRepository;
    @Override
    public CouponEntity registerCouponByAllUser(CouponVo couponVo) {
        CouponEntity coupon = CouponEntity.builder()
                .name(couponVo.getName())
                .createAdminId(couponVo.getCreateAdminId())
                .salePercent(couponVo.getSalePercent())
                .createAt(couponVo.getCreatAt())
                .aggregateIdentifier(couponVo.getAggregateIdentifier())
                .build();

        List<CouponComponentEntity> couponComponentEntityList = couponVo.getCouponComponentVoList().stream()
                .map(couponComponentVo -> CouponComponentEntity.builder()
                        .userId(couponComponentVo.getUserId())
                        .endAt(couponComponentVo.getEndAt())
                        .coupon(coupon)
                        .status(couponComponentVo.getStatus())
                        .build())
                .collect(Collectors.toList());

        coupon.setCouponComponents(couponComponentEntityList);

        return couponEntityRepository.save(coupon);
    }


    @Override
    public CouponEntity findCouponByCouponId(CouponVo.CouponId couponId) {
        return couponEntityRepository.findById(couponId.getId()).orElseThrow(()->new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"findCouponByCouponComponentId()"));
    }
}
