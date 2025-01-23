package com.example.order.adapter.axon.command;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestCommand {
    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private Long couponId;

    public ProductRequestCommand(RegisterOrderCommand.ProductCommand productCommand) {
        this.productId = productCommand.getProductId();
        this.colorId = productCommand.getColorId();
        this.sizeId = productCommand.getSizeId();
        this.amount = productCommand.getAmount();
        this.couponId = productCommand.getCouponId();
    }
}
