package org.example.basket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;

import java.time.LocalDateTime;

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
            String productName
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
                productName
        );
    }

}
