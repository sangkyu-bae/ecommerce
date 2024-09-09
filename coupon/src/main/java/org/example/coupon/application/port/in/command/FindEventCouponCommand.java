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
public class FindEventCouponCommand extends SelfValidating<FindEventCouponCommand> {

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    public FindEventCouponCommand(LocalDateTime startAt, LocalDateTime endAt){
        this.startAt = startAt;
        this.endAt = endAt;
        this.validateSelf();
    }
}
