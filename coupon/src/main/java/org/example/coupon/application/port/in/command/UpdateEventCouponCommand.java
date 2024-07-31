package org.example.coupon.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;
import org.example.coupon.application.port.out.UpdateEventCouponPort;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UpdateEventCouponCommand extends SelfValidating<UpdateEventCouponPort> {

    @NotNull
    private long eventId;

    @NotNull
    private long userId;


    public UpdateEventCouponCommand(long eventId, long userId){
        this.eventId = eventId;
        this.userId = userId;

        this.validateSelf();
    }

}
