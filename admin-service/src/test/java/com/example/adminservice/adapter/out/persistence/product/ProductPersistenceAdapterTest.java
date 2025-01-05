package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.out.persistence.entity.*;
import com.example.adminservice.adapter.out.persistence.repository.*;
import com.example.adminservice.application.port.in.command.ExistProductCommand;
import com.example.adminservice.domain.ProductVo;
import com.example.adminservice.domain.SizeVo;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductPersistenceAdapterTest {

    @Autowired
    private SpringDataProductRepository springDataProductRepository;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    @Autowired
    private SizeEntityRepository sizeEntityRepository;

    @Autowired
    private ColorEntityRepository colorEntityRepository;

    @Autowired
    private BrandEntityRepository brandEntityRepository;
    @Autowired
    private ProductPersistenceAdapter productPersistenceAdapter;

    @Autowired
    private ProductComponentEntityRepository productComponentEntityRepository;

    @AfterEach
    void tearDown(){
        springDataProductRepository.deleteAll();
        categoryEntityRepository.deleteAll();
        sizeEntityRepository.deleteAll();
        colorEntityRepository.deleteAll();
    }

    @Test
    @DisplayName("상품정보를 등록한다.")
    void createProduct() {
        //given

        String name = "product";
        int price = 1000;
        String image = "image";
        String aggregateIdentifier = "aggregate";
        String description = "content";

        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        BrandEntity brand = BrandEntity.builder()
                .name("nike")
                .build();

        BrandEntity saveBrand = brandEntityRepository.save(brand);

        ProductVo productVo = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(null),
                new ProductVo.ProductName(name),
                new ProductVo.ProductPrice(price),
                new ProductVo.ProductDescription(description),
                new ProductVo.ProductImage(image),
                new ProductVo.ProductAggregateIdentifier(aggregateIdentifier),
                new ProductVo.ProductBrandVo(saveBrand.getId(),saveBrand.getName()),
                new ProductVo.ProductCategoryVo(saveCategory.getId(),saveCategory.getName()),
                new HashSet<>()
        );
        //when
        ProductEntity saveProductEntity = productPersistenceAdapter.createProduct(productVo);

        //then
        assertThat(saveProductEntity).extracting("name", "price", "description","productImage","aggregateIdentifier")
                .contains(name
                        ,price
                        ,description
                        ,image
                        ,aggregateIdentifier);
    }



    @Test
    @DisplayName("상품 ID 기반 상품을 조회한다.")
    void findProduct() {
        //given
        ProductEntity productEntity = ProductEntity.builder()
                .name("product")
                .price(1000)
                .productImage("image" )
                .description("content")
                .build();

        ProductEntity saveProductEntity = springDataProductRepository.save(productEntity);

        //when
        ProductEntity findProduct = productPersistenceAdapter.findProduct(new ProductVo.ProductId(saveProductEntity.getId()));

        //then
        assertThat(findProduct).extracting("id", "name", "price", "description","productImage")
                .contains(saveProductEntity.getId()
                        ,saveProductEntity.getName()
                        ,saveProductEntity.getPrice()
                        ,saveProductEntity.getDescription()
                        ,saveProductEntity.getProductImage());
    }


    @Test
    @DisplayName("상품 ID 기반으로 상품을 조회시 상품이 존재하지 않을 시 PRODUCT_NO_CONTENT 에러를 반환하다.")
    void findProductNoExistProduct() {
        //given
        ProductEntity productEntity = ProductEntity.builder()
                .name("product")
                .price(1000)
                .productImage("image" )
                .description("content")
                .build();

        springDataProductRepository.save(productEntity);

        //when / then

        assertThatThrownBy(()->productPersistenceAdapter.findProduct(new ProductVo.ProductId(0L)))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }

    @Test
    @DisplayName("저장된 상품을 페이징 정보에따라 상품 페이징 정보를 리턴한다")
    void findPagingProduct() {
        //given
        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = create(i);
            springDataProductRepository.save(productEntity);
        }

        //when
        Pageable pageable = PageRequest.of(1,8, Sort.Direction.ASC,"id" );
        Page<ProductEntity> pagingProduct = productPersistenceAdapter.findPagingProduct(pageable);

        List<ProductEntity> productEntities = pagingProduct.getContent();

        assertThat(productEntities).hasSize(1)
                .extracting("name","price","productImage","description")
                .containsExactlyInAnyOrder(
                        tuple("product9", 9000,"image9","content9")
                );
    }


    @Test
    @DisplayName("저장된 상품정보를  페이징 요청시 상품 리스트 목록 크기 보다 큰 값을 요청시, 에러를 리턴한다.")
    void findPagingProductWithOverIndex() {
        //given
        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = create(i);
            springDataProductRepository.save(productEntity);
        }


        //when / then
        Pageable pageable = PageRequest.of(2,8, Sort.Direction.ASC,"id" );
        assertThatThrownBy(()-> productPersistenceAdapter.findPagingProduct(pageable))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());

    }



    @Test
    @DisplayName("상품정보를 카테고리별 페이징 하여 정보를 가져온다.")
    void findPagingProductByCategory() {

        //given
        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = create(i);
            springDataProductRepository.save(productEntity);
        }

        //when
        Pageable pageable = PageRequest.of(1,8, Sort.Direction.ASC,"id" );
        Page<ProductEntity> pagingProductEntities = productPersistenceAdapter.findPagingProductByCategory(pageable,saveCategory.getId());

        //then
        List<ProductEntity> productEntities = pagingProductEntities.getContent();

        assertThat(productEntities).hasSize(1)
                .extracting("name","price","productImage","description")
                .containsExactlyInAnyOrder(
                        tuple("product9", 9000,"image9","content9")
                );

    }

    @Test
    @DisplayName("저장된 상품정보를 카테고리별 페이징 요청시 상품 리스트 목록 크기 보다 큰 값을 요청시, 에러를 리턴한다.")
    void findPagingProductByCategoryWithOverIndex() {

        //given
        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = create(i);
            springDataProductRepository.save(productEntity);
        }

        //when/then
        Pageable pageable = PageRequest.of(2,8, Sort.Direction.ASC,"id" );
        assertThatThrownBy(()-> productPersistenceAdapter.findPagingProductByCategory(pageable,saveCategory.getId()))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }

    @Test
    @DisplayName("상품사이즈 id 기준 상품정보가 존재하는지 확인한다. - 상품 존재하는 경우")
    void existProductBySize() {

        //given
        SizeEntity saveEntity = createSizeProduct();


        //when
        boolean hasProductBySizeId = productPersistenceAdapter.existProductBySize(saveEntity.getId());

        //then
        assertThat(hasProductBySizeId).isTrue();

    }

    @Test
    @DisplayName("상품사이즈 id 기준 상품정보가 존재하는지 확인한다. - 상품 존재하지 않는 경우 false를리턴한다")
    void existProductBySizeIdWithNoSizeId() {

        //given
        SizeEntity saveEntity = createSizeProduct();

        //when
        boolean hasProductBySizeId = productPersistenceAdapter.existProductBySize(0);

        //then
        assertThat(hasProductBySizeId).isFalse();
    }

    @Test
    @DisplayName("상품 id List를 통해 기준 상품정보를 찾는다.")
    void findProductByProductIds() {

        List<Long> ids = new ArrayList<>();
        //given
        for(int i = 1; i <= 8 ;i++){
            ProductEntity productEntity = create(i);
            ProductEntity saveProduct = springDataProductRepository.save(productEntity);
            ids.add(saveProduct.getId());
        }

        //when
        List<ProductEntity> productEntities = productPersistenceAdapter.findProductByProductIds(ids);

        //then
        assertThat(productEntities).hasSize(8)
                .extracting("name","price","productImage","description")
                .containsExactlyInAnyOrder(
                        tuple("product1", 1000,"image1","content1"),
                        tuple("product2", 2000,"image2","content2"),
                        tuple("product3", 3000,"image3","content3"),
                        tuple("product4", 4000,"image4","content4"),
                        tuple("product5", 5000,"image5","content5"),
                        tuple("product6", 6000,"image6","content6"),
                        tuple("product7", 7000,"image7","content7"),
                        tuple("product8", 8000,"image8","content8")
                );
    }

    @Test
    @DisplayName("상품 id를 통해 해당 상품의 정보를 변경한다.")
    void updateProduct() {
        //given
        ProductEntity productEntity = create(1);

        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        BrandEntity brand = BrandEntity.builder()
                .name("nike")
                .build();

        BrandEntity saveBrand = brandEntityRepository.save(brand);

        productEntity.setCategory(saveCategory);
        productEntity.setBrand(saveBrand);
        ProductEntity saveProduct = springDataProductRepository.save(productEntity);

        //when
        String name = "changeProduct";
        int price = 2000;
        String image = "changeImage";
        String aggregateIdentifier = "changeAggregate";
        String description = "changeContent";

        ProductVo productVo = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(saveProduct.getId()),
                new ProductVo.ProductName(name),
                new ProductVo.ProductPrice(price),
                new ProductVo.ProductDescription(description),
                new ProductVo.ProductImage(image),
                new ProductVo.ProductAggregateIdentifier(aggregateIdentifier),
                new ProductVo.ProductBrandVo(saveBrand.getId(),saveBrand.getName()),
                new ProductVo.ProductCategoryVo(saveCategory.getId(),saveCategory.getName()),
                new HashSet<>()
        );

        ProductEntity updateProduct = productPersistenceAdapter.updateProduct(productVo);
        //then

        assertThat(updateProduct).extracting("id", "name", "price", "description","productImage")
                .contains(productVo.getId()
                        ,productVo.getName()
                        ,productVo.getPrice()
                        ,productVo.getDescription()
                        ,productVo.getProductImage());
    }


    @Test
    @DisplayName("없는 상품 id를 통해 해당 상품의 정보를 변경시도시 에러를 반환한다.")
    void updateProductWithNoProduct() {
        //given
        ProductEntity productEntity = create(1);

        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        BrandEntity brand = BrandEntity.builder()
                .name("nike")
                .build();

        BrandEntity saveBrand = brandEntityRepository.save(brand);

        productEntity.setCategory(saveCategory);
        productEntity.setBrand(saveBrand);
        ProductEntity saveProduct = springDataProductRepository.save(productEntity);

        //when / then
        String name = "changeProduct";
        int price = 2000;
        String image = "changeImage";
        String aggregateIdentifier = "changeAggregate";
        String description = "changeContent";

        ProductVo productVo = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(0L),
                new ProductVo.ProductName(name),
                new ProductVo.ProductPrice(price),
                new ProductVo.ProductDescription(description),
                new ProductVo.ProductImage(image),
                new ProductVo.ProductAggregateIdentifier(aggregateIdentifier),
                new ProductVo.ProductBrandVo(saveBrand.getId(),saveBrand.getName()),
                new ProductVo.ProductCategoryVo(saveCategory.getId(),saveCategory.getName()),
                new HashSet<>()
        );

        assertThatThrownBy(()-> productPersistenceAdapter.updateProduct(productVo))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }

    @Test
    @DisplayName("수정 id 정보를 null을 통해 해당 상품의 정보를 변경시도시 에러를 반환한다.")
    void updateProductWithNullProductId() {
        //given
        ProductEntity productEntity = create(1);

        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        BrandEntity brand = BrandEntity.builder()
                .name("nike")
                .build();

        BrandEntity saveBrand = brandEntityRepository.save(brand);

        productEntity.setCategory(saveCategory);
        productEntity.setBrand(saveBrand);
        ProductEntity saveProduct = springDataProductRepository.save(productEntity);

        //when / then
        String name = "changeProduct";
        int price = 2000;
        String image = "changeImage";
        String aggregateIdentifier = "changeAggregate";
        String description = "changeContent";

        ProductVo productVo = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(0L),
                new ProductVo.ProductName(name),
                new ProductVo.ProductPrice(price),
                new ProductVo.ProductDescription(description),
                new ProductVo.ProductImage(image),
                new ProductVo.ProductAggregateIdentifier(aggregateIdentifier),
                new ProductVo.ProductBrandVo(saveBrand.getId(),saveBrand.getName()),
                new ProductVo.ProductCategoryVo(saveCategory.getId(),saveCategory.getName()),
                new HashSet<>()
        );

        assertThatThrownBy(()-> productPersistenceAdapter.updateProduct(productVo))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }

    @Test
    @DisplayName("상품 id 통해 상품을 삭제한다.")
    void removeProduct() {

        //given
        ProductEntity productEntity = create(1);
        ProductEntity saveProduct = springDataProductRepository.save(productEntity);

        //when
        boolean isRemove = productPersistenceAdapter.removeProduct(new ProductVo.ProductId(saveProduct.getId()));

        //then
        assertThat(isRemove).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 상품 id 통해 상품을 삭제시 에러를 반환한다.")
    void removeProductWithNoProductId() {

        //given
        ProductEntity productEntity = create(1);
        ProductEntity saveProduct = springDataProductRepository.save(productEntity);

        //then/ then

        assertThatThrownBy(()-> productPersistenceAdapter.removeProduct(new ProductVo.ProductId(0L)))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }

    @Test
    void updateProductSize() {

        //given
        SizeEntity saveEntity = createSizeProduct();
        //when
        SizeVo sizeVo = SizeVo.createGenerateSizeVo(
                new SizeVo.SizeId(saveEntity.getId()),
                new SizeVo.Size(saveEntity.getSize()),
                new SizeVo.Quantity(2)
        );
        //then
    }

    private static ProductEntity create(int i) {
        ProductEntity productEntity = ProductEntity.builder()
                .name("product"+ i)
                .price(i *1000)
                .productImage("image" + i)
                .description("content" + i)
                .build();
        return productEntity;
    }

    private SizeEntity createSizeProduct() {
        ColorEntity colorEntity = ColorEntity.builder()
                .name("red")
                .build();
        ColorEntity saveColor =  colorEntityRepository.save(colorEntity);

        ProductEntity productEntity = ProductEntity.builder()
                .name("product")
                .price(1000)
                .productImage("image")
                .description("content")
                .build();
        springDataProductRepository.save(productEntity);

        ProductComponentEntity productComponent = ProductComponentEntity.builder()
                .color(saveColor)
                .product(productEntity)
                .build();

        productComponentEntityRepository.save(productComponent);

        SizeEntity sizeEntity = SizeEntity.builder()
                .size(230)
                .quantity(3)
                .productComponent(productComponent)
                .build();

        SizeEntity saveEntity = sizeEntityRepository.save(sizeEntity);

        productComponent.addSize(saveEntity);

        return saveEntity;
    }
}