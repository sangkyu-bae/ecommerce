package com.example.adminservice.domain.product.dto;

import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateProductDto extends ProductDto{

    @NotNull
    private Brand brand;

    @NotNull
    private Category category;

    @NotNull
    List<ColorDataDto> colorDataList;
}

