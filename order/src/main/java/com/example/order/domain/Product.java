package com.example.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Product {

    Long id;

    private long productId;

    private String productName;

    private long sizeId;

    private int amount;

    private int orderAmount; //주문시 수량

    private long couponId;

    private int salePrice; //할인 금액

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public static Product createGenerateProduct(Long id,
                                                long productId,
                                                String productName,
                                                long sizeId,
                                                int amount,
                                                int orderAmount,
                                                Long couponId,
                                                int salePrice,
                                                LocalDateTime createAt,
                                                LocalDateTime updateAt
                                                ){

        return new Product(
                id,
                productId,
                productName,
                sizeId,
                amount,
                orderAmount,
                couponId,
                salePrice,
                createAt,
                updateAt
        );

    }
}
