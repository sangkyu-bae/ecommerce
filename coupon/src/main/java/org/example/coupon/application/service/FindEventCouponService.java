package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.EventMapper;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.FindAuthEventCouponCommand;
import org.example.coupon.application.port.in.command.FindEventCouponCommand;
import org.example.coupon.application.port.in.usecase.FindEventUseCase;
import org.example.coupon.application.port.out.FindCouponPort;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FindEventCouponService implements FindEventUseCase {

    private final FindEventPort findEventPort;
    private final EventMapper eventMapper;

    private final FindCouponPort findCouponPort;

    @Override
    public List<Event> findEventCoupon(FindEventCouponCommand command) {
        List<EventEntity> eventEntityList = findEventPort.findByStartAtBeforeAndEndAtGreaterThanEqual(command.getStartAt(),command.getEndAt());

        return eventEntityList.stream()
                .map(event ->eventMapper.mapToDomain(event,false))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findWithAuthByEventCoupon(FindAuthEventCouponCommand command) {
        List<EventEntity> eventEntityList = findEventPort.findByStartAtBeforeAndEndAtGreaterThanEqual(command.getStartAt(),command.getEndAt());

        List<Event> eventList = new ArrayList<>();

        for(EventEntity eventEntity : eventEntityList) {
            CouponEntity coupon = findCouponPort.findByCouponName(new Coupon.CouponName(eventEntity.getCouponName()));
            boolean existCoupon = coupon.existCouponByUserId(command.getUserId());
            Event event;
            if(existCoupon){
                event = eventMapper.mapToDomain(eventEntity,true);
            }else {
                event = eventMapper.mapToDomain(eventEntity,false);
            }

            eventList.add(event);
        }

        return eventList;
    }

}
