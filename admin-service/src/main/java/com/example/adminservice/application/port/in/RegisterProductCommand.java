package com.example.adminservice.application.port.in;

import com.example.adminservice.common.SelfValidating;
import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import com.example.adminservice.domain.product.dto.ColorDataDto;
import com.example.adminservice.domain.product.dto.CreateProductDto;
import com.example.adminservice.domain.product.dto.ProductDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
//public class RegisterProductCommand extends SelfValidating<RegisterProductCommand> {
public class RegisterProductCommand extends SelfValidating<RegisterProductCommand> {

    @NotNull
    private Brand brand;

    @NotNull
    private Category category;

    @NotNull
    List<ColorDataDto> colorDataList;

    @NotBlank
    @Length(min = 2, max = 30)
    private String name;

    @NotNull
    private int price;

    @NotBlank
    @Length(min = 15)
    private String description;

    private String productImage;

    public RegisterProductCommand(
            Brand brand,
            Category category,
            List<ColorDataDto> colorDataList,
            String name,
            int price,
            String description,
            String productImage
    ) {
        this.brand = brand;
        this.category = category;
        this.colorDataList = colorDataList;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productImage = productImage;

        this.validateSelf();
    }

}
