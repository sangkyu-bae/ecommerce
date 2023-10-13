package com.example.order.adapter.out.external.product;

import lombok.*;

@Getter
@AllArgsConstructor
public class ProductInfoRequest {

    private final Long productId;

    private final Long colorId;

    private final int amount;

    private final Long sizeId;

    private final Long orderId;


    public static ProductInfoRequest createGenerateProductRequest(long productId,
                                                                  long colorId,
                                                                  int amount,
                                                                  long sizeId,
                                                                  long orderId){
        return new ProductInfoRequest(productId,colorId,amount,sizeId,orderId);
    }
}
