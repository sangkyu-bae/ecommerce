package org.example.coupon.adapter.axon.command;

import lombok.*;
import org.example.coupon.domain.CouponComponentVo;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponRequestCreateCommand {

    private Long createAdminId;

    private int salePercent;

    private String name;

    private LocalDateTime createAt;
    private List<CouponComponentRequestCreateCommand> couponComponentRequestCreateCommands;

    @AllArgsConstructor
    @Getter
    public static class CouponComponentRequestCreateCommand {
        private Long userId;

        private int status;

        private LocalDateTime endAt;

    }
}
