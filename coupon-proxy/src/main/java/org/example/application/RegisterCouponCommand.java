package org.example.application;

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
public class RegisterCouponCommand extends SelfValidating<RegisterCouponCommand> {

    @NotNull
    private long eventId;

    @NotNull
    private long userId;

    public RegisterCouponCommand(long eventId, long userId){
        this.eventId = eventId;
        this.userId = userId;
        this.validateSelf();
    }
}
