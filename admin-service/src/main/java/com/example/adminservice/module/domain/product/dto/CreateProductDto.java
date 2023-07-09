package com.example.adminservice.module.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateProductDto {

    @NotBlank
    @Length(min = 2,max = 30)
    private String name;

    @NotNull
    private int price;

    @NotBlank
    private String description;

    private String productImage;

    private String brandName;

    private String categoryName;

    List<ColorWithSizeAndQuantity> sizeAndQuantities;
}
@Data
 class ColorWithSizeAndQuantity{
    private String colorName;
    List<SizeAndQuantity> sizeAndQuantities;
}

@Data
 class SizeAndQuantity{
    private int size;
    private int quantity;
}