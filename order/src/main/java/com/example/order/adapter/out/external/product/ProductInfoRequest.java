package com.example.order.adapter.out.external.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductInfoRequest {

    private Long productId;

    private Long colorId;

    private int amount;


    public static ProductInfoRequest createGenerateProductRequest(Long productId, Long colorId, int amount){
        return new ProductInfoRequest(productId,colorId,amount);
    }
}
