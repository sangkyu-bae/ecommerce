package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.FindCouponCommand;
import org.example.coupon.domain.Coupon;

public interface FindCouponUseCase {

    Coupon findCouponByCouponId(FindCouponCommand command);
}
