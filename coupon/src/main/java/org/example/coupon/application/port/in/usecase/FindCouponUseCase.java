package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.FindCouponCommand;
import org.example.coupon.domain.CouponVo;

public interface FindCouponUseCase {

    CouponVo findCouponByCouponId(FindCouponCommand command);
}
