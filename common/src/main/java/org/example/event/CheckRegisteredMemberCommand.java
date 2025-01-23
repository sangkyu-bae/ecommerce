package org.example.event;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@ToString
public class CheckRegisteredMemberCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String createOrderId;

    private String checkRegisteredMemberId;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private List<ProductRequestCreateCommand> productRequestCreateEvents;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
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
