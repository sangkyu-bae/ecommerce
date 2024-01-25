package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;


@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateEventService implements UpdateEventCouponUseCase {



    @Override
    public boolean couponIssuance(CouponIssuanceCommand command) {

        return false;
    }
}
