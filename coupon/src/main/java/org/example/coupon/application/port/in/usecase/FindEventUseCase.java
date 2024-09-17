package org.example.coupon.application.port.in.usecase;

import org.example.coupon.application.port.in.command.FindAuthEventCouponCommand;
import org.example.coupon.application.port.in.command.FindEventCouponCommand;
import org.example.coupon.domain.Event;

import java.util.List;

public interface FindEventUseCase {

    List<Event> findEventCoupon(FindEventCouponCommand command);

    List<Event> findWithAuthByEventCoupon(FindAuthEventCouponCommand command);
}
