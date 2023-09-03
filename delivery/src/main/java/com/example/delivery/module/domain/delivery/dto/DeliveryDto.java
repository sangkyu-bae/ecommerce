package com.example.delivery.module.domain.delivery.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class DeliveryDto {
    @NotNull
    private Long productId;

    @NotNull
    private Long colorId;

    @NotNull
    private Long sizeId;

    @NotNull
    private Long orderId;

    @NotNull
    private int amount;

    @NotBlank
    @Length(min = 3)
    private String address;

    @NotBlank
    private int payment;

    @NotNull
    private LocalDate createAt;
}
