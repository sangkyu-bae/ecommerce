package org.example.adapter.out.service.basket;

import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class Basket {

    private Long id;

    private long productSizeId;

    private long memberId;

    private int productQuantity;

    private int status;

    private long productId;

    private int size;

    private String colorName;

    private LocalDateTime creatAt;

    private LocalDateTime updateAt;

    @Builder
    public Basket(Long id, long productSizeId, long memberId, int productQuantity, int status, long productId, int size, String colorName, LocalDateTime creatAt, LocalDateTime updateAt) {
        this.id = id;
        this.productSizeId = productSizeId;
        this.memberId = memberId;
        this.productQuantity = productQuantity;
        this.status = status;
        this.productId = productId;
        this.size = size;
        this.colorName = colorName;
        this.creatAt = creatAt;
        this.updateAt = updateAt;
    }
}
