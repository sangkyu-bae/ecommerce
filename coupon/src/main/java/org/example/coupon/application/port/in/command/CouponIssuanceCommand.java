package org.example.coupon.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CouponIssuanceCommand extends SelfValidating<CouponIssuanceCommand> {

    @NotNull
    private long eventCouponId;

    @NotNull
    private long userId;

    public CouponIssuanceCommand(long eventCouponId,long userId){
        this.eventCouponId = eventCouponId;
        this.userId = userId;
        this.validateSelf();
    }
}
