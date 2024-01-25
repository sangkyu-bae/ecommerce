package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.EventMapper;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.RegisterEventCouponCommand;
import org.example.coupon.application.port.in.usecase.RegisterEventCouponUseCase;
import org.example.coupon.application.port.out.RegisterEventPort;
import org.example.coupon.domain.Event;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class RegisterEventService implements RegisterEventCouponUseCase {

    private final RegisterEventPort registerEventPort;

    private final EventMapper eventMapper;

    @Override
    public Event registerEventCoupon(RegisterEventCouponCommand command) {

        Event registerEvent = Event.createGenerateEvent(
                new Event.EventId(null),
                new Event.EventCouponName(command.getCouponName()),
                new Event.EventSalePercent(command.getSalePercent()),
                new Event.EventQuantity(command.getQuantity()),
                new Event.EventStartAt(command.getStartAt()),
                new Event.EventEndAt(command.getEndAt())
        );

        EventEntity registerEventEntity = registerEventPort.registerEvent(registerEvent);

        return eventMapper.mapToDomain(registerEventEntity);
    }
}
