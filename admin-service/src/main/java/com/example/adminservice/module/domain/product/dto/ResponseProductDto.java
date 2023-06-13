package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.color.dto.ColorDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ResponseProductDto {

    private String name;

    private int price;

    private String description;

    private String productImage;

    private BrandDto brandDto;

    private CategoryDto categoryDto;

    private List<ColorProductDto> colorProductDtoList;
}
