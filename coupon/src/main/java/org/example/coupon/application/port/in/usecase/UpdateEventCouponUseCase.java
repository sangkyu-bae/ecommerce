package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.CouponIssuanceCommand;

public interface UpdateEventCouponUseCase {
    boolean decreaseEventCoupon(String lockName, CouponIssuanceCommand command);
}
