package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.RegisterEventCouponCommand;
import org.example.coupon.domain.Event;

public interface RegisterEventCouponUseCase {
    Event registerEventCoupon(RegisterEventCouponCommand command);
}
