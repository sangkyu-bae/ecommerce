package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.RegisterCouponCommand;
import org.example.coupon.domain.Coupon;

public interface RegisterCouponUseCase {
    Coupon RegisterCouponByAllUser(RegisterCouponCommand command);

    Coupon RegisterCouponByAllUserWithAxon(RegisterCouponCommand command);
}

