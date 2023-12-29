package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredMemberEvent {

    private long memberId;

    private String createOrderId;

    private String checkRegisteredMemberId;
    private long productId;
    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private boolean status;

    private Long couponId;
}
