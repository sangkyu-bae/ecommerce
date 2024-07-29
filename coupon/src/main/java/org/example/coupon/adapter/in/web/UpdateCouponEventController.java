package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class UpdateCouponEventController {

    private final UpdateEventCouponUseCase updateEventCouponUseCase;

    @Operation(summary = "register event", description = "이벤트 쿠폰 발급 받기 (분산락)")
    @PatchMapping("/coupon/event/lock/{eventId}/{couponName}")

    public ResponseEntity<String> processCouponIssuanceLock(@PathVariable("eventId") long eventId,
                                                 @PathVariable("couponName") String couponName,
                                                 @RequestHeader("X-User-Id") long userId){
        CouponIssuanceCommand command =CouponIssuanceCommand.builder()
                .eventCouponId(eventId)
                .userId(userId)
                .build();

        boolean isIssuanceCoupon =  updateEventCouponUseCase.decreaseEventCoupon(couponName,command);

        return ResponseEntity.ok().body(isIssuanceCoupon ? "success" : "fail");
    }

    @Operation(summary = "issuance event Coupon", description = "이벤트 쿠폰 발급 받기  (대기열)")
    @PatchMapping("/coupon/event/queue/{eventId}")
    public ResponseEntity<String> processCouponIssuanceQueue(@PathVariable("eventId") long eventId){
        return null;
    }
}
