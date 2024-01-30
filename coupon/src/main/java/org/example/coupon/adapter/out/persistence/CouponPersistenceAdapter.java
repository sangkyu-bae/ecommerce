package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.CouponComponentEntity;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.persistence.repository.CouponComponentEntityRepository;
import org.example.coupon.adapter.out.persistence.repository.CouponEntityRepository;
import org.example.coupon.application.port.out.FindCouponPort;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.application.port.out.UpdateCouponPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.infra.error.CouponErrorCode;
import org.example.coupon.infra.error.ErrorException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class CouponPersistenceAdapter implements RegisterCouponPort, FindCouponPort, UpdateCouponPort {
    private final CouponComponentEntityRepository couponComponentEntityRepository;

    private final CouponEntityRepository couponEntityRepository;
    @Override
    public CouponEntity registerCouponByAllUser(Coupon couponVo) {
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
    @Transactional(readOnly = false)
    public CouponComponentEntity issuanceCoupon(CouponComponent couponComponent,  Coupon.CouponId couponId) {

        CouponEntity coupon = findCouponByCouponId(couponId);

        CouponComponentEntity couponComponentEntity = CouponComponentEntity.builder()
                .coupon(coupon)
                .userId(couponComponent.getUserId())
                .status(couponComponent.getStatus())
                .endAt(couponComponent.getEndAt())
                .build();

        CouponComponentEntity registerComponent = couponComponentEntityRepository.save(couponComponentEntity);

        coupon.addCouponComponent(registerComponent);

        return registerComponent;
    }


    @Override
    public CouponEntity findCouponByCouponId(Coupon.CouponId couponId) {
        return couponEntityRepository.findById(couponId.getId())
                .orElseThrow(()->new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"findCouponByCouponComponentId"));
    }

    @Override
    public CouponEntity updateCoupon(long couponId, long userId) {
        CouponEntity couponEntity = couponEntityRepository.findById(couponId)
                .orElseThrow(()->new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"findCouponByCouponComponentId"));

        couponEntity.checkAndUpdateCoupon(userId);

        return couponEntityRepository.save(couponEntity);
    }
}
