package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.color.dto.ColorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDto extends ProductDto {

    private Long id;

    private BrandDto brandDto;

    private CategoryDto categoryDto;

    private List<ColorProductDto> colorProductDtoList;


}
