package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.RegisterCouponCommand;
import org.example.coupon.domain.CouponVo;

public interface RegisterCouponUseCase {
    CouponVo RegisterCouponByAllUser(RegisterCouponCommand command);

    CouponVo RegisterCouponByAllUserWithAxon(RegisterCouponCommand command);
}

