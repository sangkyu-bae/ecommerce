package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredCouponCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String createOrderId;

    private String checkRegisteredCoupon;

    private long couponId;

    private long productSizeId;

    private int productAmount;

}
