package org.example.basket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.adapter.out.service.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketAggregationVo {

    private Long id;

    private long productSizeId;

    private long productId;

    private int size;

    private int price;

    private long memberId;

    private int productQuantity;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

    private String productName;

    private String colorName;

    private Product.Brand brand;

    private List<Product.ProductComponentEntityVo> productComponentEntityVoList;

    public static BasketAggregationVo createGenerate(
            long id,
            long productSizeId,
            long productId,
            int size,
            int price,
            long memberId,
            int productQuantity,
            int status,
            LocalDateTime createAt,
            LocalDateTime updateAt,
            String productName,
            String colorName,
            Product.Brand brand,
            List<Product.ProductComponentEntityVo> productComponentEntityVos
    ) {
        return new BasketAggregationVo(
                id,
                productSizeId,
                productId,
                size,
                price,
                memberId,
                productQuantity,
                status,
                createAt,
                updateAt,
                productName,
                colorName,
                brand,
                productComponentEntityVos
        );
    }

}
