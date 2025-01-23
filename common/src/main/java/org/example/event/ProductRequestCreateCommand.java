package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequestCreateCommand {
    private long sizeId;

    private int amount;

    private Long couponId;

    public ProductRequestCreateCommand(long sizeId, int amount, long couponId) {
        this.sizeId = sizeId;
        this.amount = amount;
        this.couponId = couponId;
    }
}
