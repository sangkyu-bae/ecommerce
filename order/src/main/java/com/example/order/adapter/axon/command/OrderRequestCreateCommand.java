package com.example.order.adapter.axon.command;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestCreateCommand {

    private String createOrderId;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private List<ProductRequestCommand> productRequestCommands;

    @Value
    public static class ProductRequestCommand{
        private long productId;

        private long colorId;

        private long sizeId;

        private int amount;

        private Long couponId;

//        public ProductRequestCommand(long productId, long colorId, long sizeId, int amount,Long couponId){
//            this.productId = productId;
//            this.colorId = colorId;
//            this.sizeId = sizeId;
//            this.amount = amount;
//            this.couponId = couponId;
//        }
//
        public ProductRequestCommand(RegisterOrderCommand.ProductCommand productCommand){
            this.productId = productCommand.getProductId();
            this.colorId = productCommand.getColorId();
            this.sizeId = productCommand.getSizeId();
            this.amount = productCommand.getAmount();
            this.couponId = productCommand.getCouponId();
        }

    }
}
