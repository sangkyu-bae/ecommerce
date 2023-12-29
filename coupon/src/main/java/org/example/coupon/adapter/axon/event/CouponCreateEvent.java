package org.example.coupon.adapter.axon.event;

import lombok.*;
import org.example.coupon.adapter.axon.command.CouponRequestCreateCommand;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponCreateEvent {

    private Long createAdminId;

    private int salePercent;

    private LocalDateTime createAt;

    private String name;

    private List<CouponComponentCreateEvent>  couponComponentCreateEvents;

    @AllArgsConstructor
    @Getter
    public static class CouponComponentCreateEvent {
        private Long userId;

        private int status;

        private LocalDateTime endAt;

    }
}
