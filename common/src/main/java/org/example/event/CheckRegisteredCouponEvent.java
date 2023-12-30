package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredCouponEvent {

    private String createOrderId;

    private boolean isSuccess;

    private String checkRegisteredCoupon;

    private long couponId;

    private long productSizeId;

    private int productAmount;

    private long userId;

    private String productAggregate;
}
