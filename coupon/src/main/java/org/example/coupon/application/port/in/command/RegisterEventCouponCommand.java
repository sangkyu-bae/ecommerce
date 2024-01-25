package org.example.coupon.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterEventCouponCommand extends SelfValidating<RegisterEventCouponCommand> {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    private String couponName;

    @NotNull
    private int salePercent;

    @NotNull
    private int quantity;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    public RegisterEventCouponCommand(String couponName,
                                      int salePercent,
                                      int quantity,
                                      LocalDateTime startAt,
                                      LocalDateTime endAt){
        this.couponName = couponName;
        this.salePercent = salePercent;
        this.quantity = quantity;
        this.startAt = startAt;
        this.endAt = endAt;
        this.validateSelf();
    }
}
