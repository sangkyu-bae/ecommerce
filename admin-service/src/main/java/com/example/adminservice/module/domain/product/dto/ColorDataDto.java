package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.color.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ColorDataDto {
    private Color colorName;
    List<SizeAndQuantityDto> colorSize;
}
