package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;
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
    public ResponseEntity<String> processCouponIssuanceQueue(@PathVariable("eventId") long eventId,
                                                             @RequestHeader("X-User-Id") long userId){
        UpdateEventCouponCommand command = UpdateEventCouponCommand.builder()
                .eventId(eventId)
                .userId(userId)
                .build();

        updateEventCouponUseCase.addEventQueue(command);

        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/coupon/evnet/test")
    public ResponseEntity<String> test(){
        for(int i = 0; i < 100 ;i++){
            UpdateEventCouponCommand command = UpdateEventCouponCommand.builder()
                    .eventId(3)
                    .userId(i+1)
                    .build();

            updateEventCouponUseCase.addEventQueue(command);
        }

        updateEventCouponUseCase.process();

        return ResponseEntity.ok().body("success");
    }
}
