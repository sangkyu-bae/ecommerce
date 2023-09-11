package com.example.adminservice.domain.product.dto;

import com.example.adminservice.domain.color.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ColorDataDto {
    private Color colorDto;
    List<SizeAndQuantityDto> sizeQuantityDtoList;
}
