package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.EventMapper;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.RegisterCouponCommand;
import org.example.coupon.application.port.in.command.RegisterEventCouponCommand;
import org.example.coupon.application.port.in.usecase.RegisterCouponUseCase;
import org.example.coupon.application.port.in.usecase.RegisterEventCouponUseCase;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.application.port.out.RegisterEventPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.Event;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.UUID;

@UseCase
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class RegisterEventService implements RegisterEventCouponUseCase {

    private final RegisterEventPort registerEventPort;

    private final RegisterCouponUseCase registerCouponUseCase;

    private final EventMapper eventMapper;

    private final RegisterCouponPort registerCouponPort;

    @Override
    public Event registerEventCoupon(RegisterEventCouponCommand command) {

        /**
         * 수정 필요
         * */
        RegisterCouponCommand registerCouponCommand = RegisterCouponCommand.builder()
                .createAdminUserId(command.getAdminUser())
                .name(command.getCouponName())
                .salePercent(command.getSalePercent())
                .build();

        registerCouponUseCase.RegisterCouponByAllUserWithAxon(registerCouponCommand);

        Event registerEvent = Event.createGenerateEvent(
                new Event.EventId(null),
                new Event.EventCouponName(command.getCouponName()),
                new Event.EventSalePercent(command.getSalePercent()),
                new Event.EventQuantity(command.getQuantity()),
                new Event.EventStartAt(command.getStartAt()),
                new Event.EventEndAt(command.getEndAt()),
                new Event.EventIssued(false)
        );

        EventEntity registerEventEntity = registerEventPort.registerEvent(registerEvent);

        Coupon coupon = Coupon.createGenerateCoupon(
                new Coupon.CouponId(null),
                new Coupon.CouponCreateAdminId(command.getAdminUser()),
                new Coupon.CouponSalePercent(command.getSalePercent()),
                new Coupon.CouponName(command.getCouponName()),
                new Coupon.CouponCreateAt(LocalDateTime.now()),
                null,
                new Coupon.CouponAggregateIdentifier(UUID.randomUUID().toString())// axon등록필요
        );

        registerCouponPort.registerCoupon(coupon);

        return eventMapper.mapToDomain(registerEventEntity,false);
    }
}
