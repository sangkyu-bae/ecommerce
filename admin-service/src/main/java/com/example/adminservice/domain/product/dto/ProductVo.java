package com.example.adminservice.domain.product.dto;

import com.example.adminservice.adapter.out.persistence.product.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Set;

/**
 * Entity에 접근하는 오염되지 안되는 클래스
 * 오염이 되지 않도록
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductVo {

    private final String name;

    private final int price;

    private final String description;

    private final String productImage;

    private final BrandEntity brand;

    private final CategoryEntity category;

    private final Set<ProductComponentEntity> productComponents;

    public static ProductVo createGenerateProductVo(
            ProductName productName,
            ProductPrice productPrice,
            ProductDescription productDescription,
            ProductImage productImage,
            BrandEntity brand,
            CategoryEntity category,
            Set<ProductComponentEntity> components
    ){
      return new ProductVo(
              productName.getProductName(),
              productPrice.getPrice(),
              productDescription.getDescription(),
              productImage.getImage(),
              brand,
              category,
              components
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
