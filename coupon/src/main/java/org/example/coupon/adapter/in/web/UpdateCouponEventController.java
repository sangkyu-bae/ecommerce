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

    @Operation(summary = "register event", description = "이벤트 쿠폰 발급 받기")
    @PutMapping("/coupon/event/{eventId}")
    public ResponseEntity<String> couponIssuance(@PathVariable("eventId") long eventId,
                                                 @RequestHeader("X-User-Id") long userId){

        CouponIssuanceCommand command =CouponIssuanceCommand.builder()
                .eventCouponId(eventId)
                .userId(userId)
                .build();


        return null;
    }
}
