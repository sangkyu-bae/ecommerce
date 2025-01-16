package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CheckRegisteredProductEvent {

    private String createOrderId;


    private boolean isSuccess;

    private String checkRegisteredProductIdAndAmount;

    private Long userId;

    private String productAggregate;

    private List<CheckRegisteredProductEvent.ProductRequestCreateCommand> productRequestCreateEvents;

    @Value
    public static class ProductRequestCreateCommand {

        private long sizeId;

        private int amount;

        private Long couponId;

        public ProductRequestCreateCommand(long sizeId, int amount, long couponId) {
            this.sizeId = sizeId;
            this.amount = amount;
            this.couponId = couponId;
        }
    }

}
