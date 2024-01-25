package org.example.coupon.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEventCouponRequest {

    private String couponName;

    private int salePercent;

    private int quantity;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

}
