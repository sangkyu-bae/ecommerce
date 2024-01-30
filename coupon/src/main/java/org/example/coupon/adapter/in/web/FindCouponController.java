package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.FindCouponCommand;
import org.example.coupon.application.port.in.usecase.FindCouponUseCase;
import org.example.coupon.domain.Coupon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class FindCouponController {

    private final FindCouponUseCase findCouponUseCase;

    @Operation(summary = "find coupon by couponComponentId", description = "couponComponentId로 쿠폰 정보 가져오기")
    @GetMapping("/coupon/{couponId}")
    public ResponseEntity<Coupon> registerCoupon(@PathVariable("couponId")long couponId){
        FindCouponCommand command = FindCouponCommand.builder()
                .couponId(couponId)
                .build();

        Coupon coupon = findCouponUseCase.findCouponByCouponId(command);
        return ResponseEntity.ok().body(coupon);
    }
}
