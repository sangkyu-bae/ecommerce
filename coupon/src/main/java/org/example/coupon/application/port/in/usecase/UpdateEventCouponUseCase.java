package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;

public interface UpdateEventCouponUseCase {
    boolean decreaseEventCoupon(String lockName, CouponIssuanceCommand command);

    boolean isQueueEventCoupon(UpdateEventCouponCommand command);

    void process();

    void addEventQueue(UpdateEventCouponCommand command);
}
