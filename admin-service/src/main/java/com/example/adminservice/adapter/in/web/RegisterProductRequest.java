package com.example.adminservice.adapter.in.web;

import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import com.example.adminservice.domain.product.dto.ColorDataDto;
import com.example.adminservice.domain.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductRequest extends ProductDto {

    @NotNull
    private Brand brand;

    @NotNull
    private Category category;

    @NotNull
    List<ColorDataDto> colorDataList;
}
