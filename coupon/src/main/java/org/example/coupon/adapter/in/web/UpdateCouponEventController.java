package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.coupon.application.port.out.UpdateEventCouponPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class UpdateCouponEventController {

    private final UpdateEventCouponUseCase updateEventCouponUseCase;

    private final UpdateEventCouponPort updateEventCouponPort;

    @Operation(summary = "register event", description = "이벤트 쿠폰 발급 받기 (분산락)")
    @PatchMapping("/coupon/auth/event/lock/{eventId}/{couponName}")

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
    @PatchMapping("/coupon/auth/event/queue/{eventId}")
    public ResponseEntity<String> processCouponIssuanceQueue(@PathVariable("eventId") long eventId,
                                                             @RequestHeader("X-User-Id") long userId){
        UpdateEventCouponCommand command = UpdateEventCouponCommand.builder()
                .eventId(eventId)
                .userId(userId)
                .build();

        updateEventCouponUseCase.addEventQueue(command);

        return ResponseEntity.ok().body("success");
    }

//    private final UpdateEventCouponPort updateEventCouponPort;
    @GetMapping("/coupon/refresh")
    public ResponseEntity<String> tt(){
        updateEventCouponPort.refreshQueue(3);
        return ResponseEntity.ok().body("tt");
    }
    @GetMapping("/coupon/evnet/test")
    public ResponseEntity<String> test(){
        for(int i = 0; i < 1 ;i++){
            UpdateEventCouponCommand command = UpdateEventCouponCommand.builder()
                    .eventId(3)
                    .userId(i+1)
                    .build();

            updateEventCouponUseCase.addEventQueue(command);
        }
        long startTime = System.nanoTime();
        updateEventCouponUseCase.process();
//
//
//        // 실행 시간 계산 (밀리초 단위로 변환)
//        long endTime = System.nanoTime();
//        long duration = (endTime - startTime) / 1_000_000;
//        log.info("Process completed in {} ms", duration);
//        updateEventCouponPort.refreshQueue(3);
        return ResponseEntity.ok().body("success");
    }
}
