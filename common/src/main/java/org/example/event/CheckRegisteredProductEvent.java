package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CheckRegisteredProductEvent {

    private String createOrderId;

    private long productId;

    private long sizeId;

    private int amount;

    private boolean isSuccess;

    private String checkRegisteredProductIdAndAmount;

    private Long couponId;

}
