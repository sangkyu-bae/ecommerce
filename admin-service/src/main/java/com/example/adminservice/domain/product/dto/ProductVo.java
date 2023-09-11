package com.example.adminservice.domain.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity에 접근하는 오염되지 안되는 클래스
 * 오염이 되지 않도록
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductVo {

    @NotBlank
    @NotNull
    @Length(min = 2, max = 30)
    private final String name;

    @NotNull
    private final int price;

    @NotBlank
    @Length(min = 15)
    private final String description;

    private final String productImage;

    public static ProductVo createGenerateProductVo(
            ProductName productName,
            ProductPrice productPrice,
            ProductDescription productDescription,
            ProductImage productImage
    ){
        return new ProductVo(
                productName.productName,
                productPrice.price,
                productDescription.description,
                productImage.image
        );
    }

    @Value
    public static class ProductName{
        public ProductName(String value){
            this.productName = value;
        }
        String productName;
    }

    @Value
    public static class ProductPrice{
        public ProductPrice(int value){
            this.price = value;
        }
        int price;
    }

    @Value
    public static class ProductDescription{
        public ProductDescription(String value){
            this.description = value;
        }

        String description;
    }

    @Value
    public static class ProductImage{
        public ProductImage(String value){
            this.image = value;
        }
        String image;
    }
}
