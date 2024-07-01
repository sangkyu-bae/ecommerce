package com.example.order.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private Long id;

    private String name;

    private int price;

    private String description;

    private String productImage;

    private String aggregateIdentifier;

    private Brand brand;

    private List<ProductComponentEntityVo> productComponents;

    @Data
    @AllArgsConstructor @NoArgsConstructor
    static public class Brand{
        Long id;
        String name;
    }

    @Data
    @AllArgsConstructor @NoArgsConstructor
    public static class ProductComponentEntityVo{
        private Long id;

        private ProductColorVo color;

        private List<ProductSizeVo> sizes;
    }

    @Data
    @AllArgsConstructor @NoArgsConstructor
    public static class ProductColorVo{

        private Long id;

        private String name;
    }

    @Data
    @AllArgsConstructor @NoArgsConstructor
    public static class ProductSizeVo{

        private Long id;

        private int size;

        private int quantity;
    }

    public void settingComponent(int colorId, int sizeId){
        List<ProductComponentEntityVo> productComponentEntityVoList = new ArrayList<>();


        for(ProductComponentEntityVo productComponentEntityVo : this.productComponents){
            ProductComponentEntityVo productComponentEntity = new ProductComponentEntityVo();

            if(productComponentEntityVo.getColor().getId() == colorId){
                List<ProductSizeVo> productSizes = new ArrayList<>();

                for(ProductSizeVo productSizeVo : productComponentEntityVo.getSizes()){
                    if(productSizeVo.getId() == sizeId){
                        productSizes.add(productSizeVo);
                        break;
                    }
                }

                productComponentEntity.setColor(productComponentEntityVo.getColor());
                productComponentEntity.setSizes(productSizes);
                productComponentEntity.setId(productComponentEntity.getId());;

                productComponentEntityVoList.add(productComponentEntity);
            }
        }

        this.productComponents = productComponentEntityVoList;

    }

    public static Product cleanProduct(Product product){
        return new Product(
                product.id,
                product.name,
                product.price,
                product.description,
                product.productImage,
                product.aggregateIdentifier,
                product.brand,
                product.productComponents
        );
    }

}
