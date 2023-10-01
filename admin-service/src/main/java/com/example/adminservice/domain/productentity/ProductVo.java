package com.example.adminservice.domain.productentity;

import lombok.*;

import java.util.Set;

/**
 * Entity에 접근하는 오염되지 안되는 클래스
 * 오염이 되지 않도록
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductVo {

    private final Long id;

    private final String name;

    private final int price;

    private final String description;

    private final String productImage;

    private final ProductCategoryVo category;

    private final ProductBrandVo brand;

    private final Set<ProductComponentEntityVo> productComponents;

    public static ProductVo createGenerateProductVo(
            ProductId productId,
            ProductName productName,
            ProductPrice productPrice,
            ProductDescription productDescription,
            ProductImage productImage,
            ProductBrandVo brand,
            ProductCategoryVo category,
            Set<ProductComponentEntityVo> components
    ){
      return new ProductVo(
              productId.getId(),
              productName.getProductName(),
              productPrice.getPrice(),
              productDescription.getDescription(),
              productImage.getImage(),
              category,
              brand,
              components
      );
    }

    @Value
    public static class ProductId{
        public ProductId(int value){
            this.id = (long) value;
        }

        public ProductId(Long value){
            this.id = value;
        }
        Long id;
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

    @Value
    public static class ProductCategoryVo{
        public ProductCategoryVo(Long id,String name){
            this.id = id;
            this.name = name;
        }
        private Long id;

        private String name;
    }

    @Value
    public static class ProductBrandVo{
        public ProductBrandVo(Long id,String name){
            this.id = id;
            this.name = name;
        }
        private Long id;

        private String name;


    }

    @AllArgsConstructor
    @Value
    public static class ProductComponentEntityVo{
        public static ProductComponentEntityVo createProductComponentEntityVo(ProductColorVo productColorVo, Set<ProductSizeVo> ProductSizeVos){
            return new ProductComponentEntityVo(null,productColorVo,ProductSizeVos);
        }

        public static ProductComponentEntityVo updateProductComponentEntityVo(long id,ProductColorVo productColorVo,
                                                                              Set<ProductSizeVo> ProductSizeVos){
            return new ProductComponentEntityVo(id, productColorVo,ProductSizeVos);
        }

        public static ProductComponentEntityVo readProductComponentEntityVo(Long id, ProductColorVo productColorVo, Set<ProductSizeVo> ProductSizeVos){
            return new ProductComponentEntityVo(id,productColorVo,ProductSizeVos);
        }
        private Long id;

        private ProductColorVo color;

        private Set<ProductSizeVo> sizes;

    }
    @Value
    public static class ProductColorVo{
        public ProductColorVo(Long id, String name){
            this.id = id;
            this. name = name;
        }

        private Long id;

        private String name;
    }

    @AllArgsConstructor
    @Value
    public static class ProductSizeVo{
        public static ProductSizeVo createProductSizeVo(int size, int quantity){
            return new ProductSizeVo(null,size,quantity);
        }

        public static ProductSizeVo updateProductSizeVo(long id, int size,int quantity){
            return new ProductSizeVo(id,size,quantity);
        }

        public static ProductSizeVo readProductSizeVo(Long id, int size, int quantity){
            return new ProductSizeVo(id,size,quantity);
        }

        private Long id;

        private int size;

        private int quantity;
    }

}
