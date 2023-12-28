package org.example.coupon.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterCouponCommand extends SelfValidating<RegisterCouponCommand> {

    @NotNull
    private Long createAdminUserId;

    @NotNull
    @NotBlank
    @Length(min = 2,max = 30)
    private String name;

    @NotNull
    @Min(1) @Max(95)
    private int salePercent;



    public RegisterCouponCommand (Long userId, String name,int salePercent){
        this.createAdminUserId = userId;
        this.name = name;
        this.salePercent = salePercent;
        this.validateSelf();
    }
}
