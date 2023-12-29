package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.CouponMapper;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.application.port.in.command.FindCouponCommand;
import org.example.coupon.application.port.in.usecase.FindCouponUseCase;
import org.example.coupon.application.port.out.FindCouponPort;
import org.example.coupon.domain.CouponVo;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Slf4j
@Transactional(readOnly = true)
public class FindCouponService implements FindCouponUseCase {

    private final FindCouponPort findCouponPort;

    private final CouponMapper couponMapper;

    @Override
    public CouponVo findCouponByCouponId(FindCouponCommand command) {
        CouponEntity couponEntity = findCouponPort.findCouponByCouponId(new CouponVo.CouponId(command.getCouponId()));
        return couponMapper.mapToDomainEntity(couponEntity);
    }
}
