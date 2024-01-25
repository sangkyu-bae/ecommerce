package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.adapter.in.request.RegisterCouponRequest;
import org.example.coupon.adapter.in.request.RegisterEventCouponRequest;
import org.example.coupon.application.port.in.command.RegisterEventCouponCommand;
import org.example.coupon.application.port.in.usecase.RegisterEventCouponUseCase;
import org.example.coupon.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class RegisterCouponEventController {

    private final RegisterEventCouponUseCase registerEventCouponUseCase;

    @Operation(summary = "register event", description = "이벤트 쿠폰 등록하기 선착순")
    @PostMapping("/coupon/event")
    public ResponseEntity<Event> registerEventCoupon(@RequestBody RegisterEventCouponRequest request){

        RegisterEventCouponCommand command = RegisterEventCouponCommand.builder()
                .couponName(request.getCouponName())
                .salePercent(request.getSalePercent())
                .quantity(request.getQuantity())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .build();

        //유효성 검사 추가
        Event registerEvent = registerEventCouponUseCase.registerEventCoupon(command);

        return ResponseEntity.ok().body(registerEvent);
    }
}
