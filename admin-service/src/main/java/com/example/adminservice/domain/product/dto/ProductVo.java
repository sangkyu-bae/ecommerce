package com.example.adminservice.domain.product.dto;

import com.example.adminservice.adapter.out.persistence.SizeEntity;
import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private final Brand brand;

    private final Category category;

    private final Set<SizeEntity> sizeEntities;

    public static ProductVo createGenerateProductVo(
            ProductName productName,
            ProductPrice productPrice,
            ProductDescription productDescription,
            ProductImage productImage,
            Brand brand,
            Category category,
            Set<SizeEntity> sizeEntities
    ){
      return new ProductVo(
              productName.getProductName(),
              productPrice.getPrice(),
              productDescription.getDescription(),
              productImage.getImage(),
              brand,
              category,
              sizeEntities
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
