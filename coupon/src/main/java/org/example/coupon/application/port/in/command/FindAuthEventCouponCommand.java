package org.example.coupon.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FindAuthEventCouponCommand extends SelfValidating<FindAuthEventCouponCommand> {

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    @NotNull
    private long userId;

    public FindAuthEventCouponCommand(LocalDateTime startAt, LocalDateTime endAt, long userId){
        this.startAt = startAt;
        this.endAt = endAt;
        this.userId = userId;
        this.validateSelf();
    }
}
