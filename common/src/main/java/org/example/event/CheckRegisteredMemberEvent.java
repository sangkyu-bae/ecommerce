package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredMemberEvent {

    private long memberId;

    private String createOrderId;

    private String checkRegisteredMemberId;

    private int payment;

    private String address;

    private boolean status;

    private List<CheckRegisteredMemberEvent.ProductRequestCreateCommand> productRequestCreateEvents;

    @Value
    public static class ProductRequestCreateCommand {

        private long sizeId;

        private int amount;

        private Long couponId;

        public ProductRequestCreateCommand(long sizeId, int amount, long couponId){
            this.sizeId = sizeId;
            this.amount =amount;
            this.couponId = couponId;
        }
    }
}
