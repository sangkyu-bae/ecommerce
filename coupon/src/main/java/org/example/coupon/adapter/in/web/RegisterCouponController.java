package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.adapter.in.request.RegisterCouponRequest;
import org.example.coupon.application.port.in.command.RegisterCouponCommand;
import org.example.coupon.application.port.in.usecase.RegisterCouponUseCase;
import org.example.coupon.domain.CouponVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RegisterCouponController {

    private final RegisterCouponUseCase registerCouponUseCase;

    @Operation(summary = "register coupon", description = "모든 유저에게 쿠폰 등록하기")
    @PostMapping("/coupon/all-user")
    public ResponseEntity<CouponVo> registerCoupon(@RequestBody RegisterCouponRequest request,
                                                   @RequestHeader("X-User-Id") Long userId){
        RegisterCouponCommand command = RegisterCouponCommand.builder()
                .name(request.getName())
                .createAdminUserId(userId)
                .salePercent(request.getSalePercent())
                .build();

        CouponVo createCoupon = registerCouponUseCase.RegisterCouponByAllUser(command);

        return ResponseEntity.ok().body(createCoupon);
    }

    @Operation(summary = "register coupon with axon", description = "모든 유저에게 쿠폰 등록하기 axon")
    @PostMapping("/coupon/all-user/axon")
    public ResponseEntity<CouponVo> registerCouponWithAxon(@RequestBody RegisterCouponRequest request,
                                                   @RequestHeader("X-User-Id") Long userId){
        RegisterCouponCommand command = RegisterCouponCommand.builder()
                .name(request.getName())
                .createAdminUserId(userId)
                .salePercent(request.getSalePercent())
                .build();

        CouponVo createCoupon = registerCouponUseCase.RegisterCouponByAllUserWithAxon(command);

        return ResponseEntity.ok().body(createCoupon);
    }
}
