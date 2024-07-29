package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.application.port.out.UpdateCouponPort;
import org.example.coupon.application.port.out.UpdateEventPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.error.ErrorException;
import org.example.coupon.infra.redis.DistributedLock;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@UseCase
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateEventService implements UpdateEventCouponUseCase {

    private final UpdateEventPort updateEventPort;

    private final RegisterCouponPort registerCouponPort;

    @Override
    @DistributedLock(key = "#couponName")
    public boolean decreaseEventCoupon(String couponName, CouponIssuanceCommand command) {

        updateEventPort.decreaseEventCoupon(new Event.EventId(command.getEventCouponId()));

        CouponComponent couponComponent = CouponComponent.createGenerateCouponComponentVo(
                new CouponComponent.CouponComponentId(null),
                new CouponComponent.CouponComponentUserId(command.getUserId()),
                CouponComponent.CouponStatusCode.PUBLISH,
                new CouponComponent.CouponComponentEndAt(LocalDateTime.now()),
                null
        );

        registerCouponPort.issuanceCoupon(couponComponent,new Coupon.CouponId(command.getEventCouponId()));

        return true;
    }
}
