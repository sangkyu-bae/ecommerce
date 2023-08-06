package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.brand.entity.Brand;
import com.example.adminservice.module.domain.category.entity.Category;
import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotBlank
    @Length(min = 2,max = 30)
    private String name;

    @NotNull
    private int price;

    @NotBlank
    @Length(min = 15)
    private String description;

    private String productImage;

    public ProductDto(Product product){
        this.name = Objects.requireNonNull(product.getName());
        this.price = Objects.requireNonNull(product.getPrice());
        this.description = Objects.requireNonNull(product.getDescription());
        this.productImage = product.getProductImage();
    }
}
