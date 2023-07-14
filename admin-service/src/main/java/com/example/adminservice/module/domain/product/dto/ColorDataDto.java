package com.example.adminservice.module.domain.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ColorDataDto {
    private String colorName;
    List<SizeAndQuantityDto> sizeAndQuantities;
}
