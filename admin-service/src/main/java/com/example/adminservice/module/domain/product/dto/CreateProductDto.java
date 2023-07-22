package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.brand.entity.Brand;
import com.example.adminservice.module.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateProductDto extends ProductDto{

//    @NotBlank
//    @Length(min = 2,max = 30)
//    private String name;
//
//    @NotNull
//    private int price;
//
//    @NotBlank
//    @Length(min = 15)
//    private String description;
//
//    private String productImage;

    @NotNull
    private Brand brand;

    @NotNull
    private Category category;

    @NotNull
    List<ColorDataDto> colorDataList;
}

