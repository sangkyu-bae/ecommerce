package com.example.order.adapter.axon.event;


import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrderCreatedEvent {

    private String createOrderId;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private List<ProductRequestCreateEvent> productRequestCreateEvents;

    @Value
    public static class ProductRequestCreateEvent {
        private long productId;

        private long colorId;

        private long sizeId;

        private int amount;

        private Long couponId;

        public ProductRequestCreateEvent(OrderRequestCreateCommand.ProductRequestCommand command){
            this.productId = command.getProductId();
            this.colorId = command.getColorId();
            this.sizeId = command.getSizeId();
            this.amount = command.getAmount();
            this.couponId = command.getCouponId();
        }
    }


}
