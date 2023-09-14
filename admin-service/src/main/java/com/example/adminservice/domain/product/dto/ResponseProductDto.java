package com.example.adminservice.domain.product.dto;

import com.example.adminservice.domain.brand.dto.BrandDto;
import com.example.adminservice.domain.category.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDto extends ProductDto {

    private Long id;

    private BrandDto brand;

    private CategoryDto category;

    private List<ColorProductDto> colorDataList;


}