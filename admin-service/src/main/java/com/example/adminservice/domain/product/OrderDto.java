package com.example.adminservice.domain.product;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderDto {
    @NotNull
    private Long productId;

    @NotNull
    private Long colorId;

    @NotNull
    private long quantityId;

    @NotNull
    private Long sizeId;

    @NotNull
    private int amount;

    @NotBlank
    @Length(min = 3)
    private String address;

    @NotBlank
    private int payment;

    private String userId;
}
