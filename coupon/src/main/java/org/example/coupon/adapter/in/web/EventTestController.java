package org.example.coupon.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.application.port.out.UpdateCouponPort;
import org.example.coupon.application.port.out.UpdateEventPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@WebAdapter
@RequiredArgsConstructor
@RestController
@Slf4j
public class EventTestController {


    private final UpdateEventPort updateEventPort;

    private final RegisterCouponPort registerCouponPort;
    private final UpdateEventCouponUseCase updateEventCouponUseCase;
    @PostMapping("/coupon/event/issuance-lock/{userId}")
    public ResponseEntity<String> processCouponIssuanceLock(@PathVariable("userId") Long userId){
        CouponIssuanceCommand command =CouponIssuanceCommand.builder()
                .eventCouponId(3L)
                .userId(userId)
                .build();

        boolean isIssuanceCoupon =  updateEventCouponUseCase.decreaseEventCoupon("testevent",command);
        return ResponseEntity.ok().body(isIssuanceCoupon ? "success" : "fail");
    }

    @PostMapping("/coupon/event/{userId}")
    public ResponseEntity<String> processCouponBasic(@PathVariable("userId") Long userId){
        updateEventPort.decreaseEventCoupon(new Event.EventId(3L));

        CouponComponent couponComponent = CouponComponent.createGenerateCouponComponentVo(
                new CouponComponent.CouponComponentId(null),
                new CouponComponent.CouponComponentUserId(userId),
                CouponComponent.CouponStatusCode.PUBLISH,
                new CouponComponent.CouponComponentEndAt(LocalDateTime.now()),
                null
        );

        registerCouponPort.issuanceCoupon(couponComponent,new Coupon.CouponId(3L));

        return ResponseEntity.ok().body("ok");
    }
}
