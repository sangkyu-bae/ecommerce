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
public class FindCouponCommand extends SelfValidating<FindCouponCommand> {

    @NotNull
    private long couponId;

    public FindCouponCommand(Long couponId){
        this.couponId = couponId;
        this.validateSelf();
    }
}
