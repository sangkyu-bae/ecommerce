package com.example.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderAggregationVo {

    private final Long id;

    private final int amount;

    private final int payment;

    private final int size;

    private final String productImage;

    private final String productDescription;

    private final String productName;

    private final String brandName;

    private final String colorName;

    private final TypeEnumMapper statusCode;

    public static OrderAggregationVo createGenerate(
            long id,
            int amount,
            int payment,
            int size,
            String productImage,
            String productDescription,
            String productName,
            String brandName,
            String colorName,
            TypeEnumMapper statusCode
    ){
        return new OrderAggregationVo(
                id,
                amount,
                payment,
                size,
                productImage,
                productDescription,
                productName,
                brandName,
                colorName,
                statusCode
        );
    }
}
