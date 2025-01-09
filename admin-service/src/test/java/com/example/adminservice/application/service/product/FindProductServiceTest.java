package com.example.adminservice.application.service.product;

import com.example.adminservice.adapter.out.kafka.ProductProducer;
import com.example.adminservice.adapter.out.persistence.entity.*;
import com.example.adminservice.adapter.out.persistence.repository.*;
import com.example.adminservice.application.port.in.command.*;
import com.example.adminservice.application.port.in.usecase.product.FindProductUseCase;
import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
class FindProductServiceTest {

    @Autowired
    private FindProductUseCase findProductUseCase;

    @Autowired
    private SpringDataProductRepository springDataProductRepository;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    @Autowired
    private SizeEntityRepository sizeEntityRepository;

    @Autowired
    private ColorEntityRepository colorEntityRepository;

    @Autowired
    private ProductComponentEntityRepository productComponentEntityRepository;

    @MockBean
    private ProductProducer productProducer;

    @Value("${spring.datasource.url}")
    private String url;
    @AfterEach
    void tearDown(){
        springDataProductRepository.deleteAll();
        categoryEntityRepository.deleteAll();
        sizeEntityRepository.deleteAll();
        colorEntityRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 ID 기반으로 상품을 조회시 상품이 존재하지 않을 시 PRODUCT_NO_CONTENT 에러를 반환하다.")
    void findProductWithNoExistProduct() {

        //given
        ProductEntity productEntity = ProductEntity.builder()
                .name("product")
                .price(1000)
                .productImage("image" )
                .description("content")
                .build();

        springDataProductRepository.save(productEntity);

        FindProductCommand command = FindProductCommand.builder()
                .productId(0L)
                .build();

        //when / then
        assertThatThrownBy(()->findProductUseCase.findProduct(command))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }
    @Test
    @DisplayName("상품 ID 기반으로 상품을 조회한다.")
    void findProduct() {

        //given
        ProductEntity productEntity = ProductEntity.builder()
                .name("product")
                .price(1000)
                .productImage("image" )
                .description("content")
                .build();

        ProductEntity saveProduct = springDataProductRepository.save(productEntity);

        long productId = saveProduct.getId();
        String productName = saveProduct.getName();
        int productPrice = saveProduct.getPrice();
        String productImage = saveProduct.getProductImage();
        String productDescription = saveProduct.getDescription();

        doNothing().when(productProducer).sendFindProductTaskToELK(any(ProductVo.class));

        long saveProductId = saveProduct.getId();
        FindProductCommand command = FindProductCommand.builder()
                .productId(saveProductId)
                .build();
        //when
        ProductVo productVo = findProductUseCase.findProduct(command);

        //then
        assertThat(productVo).extracting("id", "name", "price", "description","productImage")
                .contains(productId,productName,productPrice,productDescription,productImage);
    }

    @Test
    @DisplayName("상품정보를 페이징 하여 정보를 가져온다.")
    void findPagingProduct() {

        //given
        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = ProductEntity.builder()
                    .name("product"+i)
                    .price(i*1000)
                    .productImage("image" + i)
                    .description("content" + i)
                    .build();
            springDataProductRepository.save(productEntity);
        }

        //when
        FindPagingProductCommand command = FindPagingProductCommand.builder()
                .pageNum(1)
                .build();
        ProductSearchVo productSearchVo = findProductUseCase.findPagingProduct(command);
        List<ProductVo> productVoList = productSearchVo.getProductVoList();

        //then
        assertThat(productVoList).hasSize(8)
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

        assertThat(productSearchVo)
                .extracting("pageNumber", "pageSize", "totalElement", "totalPage")
                .contains(0, 8, 9L, 2);

    }
    @Test
    @DisplayName("상품 페이징 요청시 상품 리스트 목록 크기 보다 큰 값을 요청시, 에러를 리턴한다.")
    void findPagingProductWithOverIndex() {

        //given
        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = ProductEntity.builder()
                    .name("product"+i)
                    .price(i*1000)
                    .productImage("image" + i)
                    .description("content" + i)
                    .build();
            springDataProductRepository.save(productEntity);
        }


        FindPagingProductCommand command = FindPagingProductCommand.builder()
                .pageNum(3)
                .build();

        //when / then
        assertThatThrownBy(()->findProductUseCase.findPagingProduct(command))
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
            ProductEntity productEntity = ProductEntity.builder()
                    .name("product"+i)
                    .price(i*1000)
                    .productImage("image" + i)
                    .description("content" + i)
                    .category(saveCategory)
                    .build();
            springDataProductRepository.save(productEntity);
        }
        //when
        FindPagingProductByCategoryCommand command = FindPagingProductByCategoryCommand.builder()
                .categoryId(category.getId())
                .pageNum(1)
                .build();

        ProductSearchVo productSearchVo = findProductUseCase.findPagingProductByCategory(command);
        List<ProductVo> productVoList = productSearchVo.getProductVoList();
        //then
        assertThat(productVoList).hasSize(8)
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

        assertThat(productVoList)
                .extracting(product -> product.getCategory().getId())
                .containsOnly(saveCategory.getId());
    }

    @Test
    @DisplayName("상품정보를 카테고리별 페이징 요청시 상품 리스트 목록 크기 보다 큰 값을 요청시, 에러를 리턴한다.")
    void findPagingProductByCategoryWithOverIndex() {

        //given
        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        for(int i = 1; i < 10 ;i++){
            ProductEntity productEntity = ProductEntity.builder()
                    .name("product"+i)
                    .price(i*1000)
                    .productImage("image" + i)
                    .description("content" + i)
                    .category(saveCategory)
                    .build();
            springDataProductRepository.save(productEntity);
        }
        //when
        FindPagingProductByCategoryCommand command = FindPagingProductByCategoryCommand.builder()
                .categoryId(category.getId())
                .pageNum(3)
                .build();

        assertThatThrownBy(()->findProductUseCase.findPagingProductByCategory(command))
                .isInstanceOf(ErrorException.class)
                .hasMessage(ProductErrorCode.PRODUCT_NO_CONTENT.getDetail());
    }

    @Test
    @DisplayName("상품사이즈 id 기준 상품정보가 존재하는지 확인한다. - 상품 존재하는 경우")
    void existProductBySizeId() {
        //given
        SizeEntity saveEntity = createProduct();

        //when
        ExistProductCommand existProductCommand = ExistProductCommand.builder()
                .sizeId(saveEntity.getId())
                .build();

        //then
        boolean hasProductBySizeId = findProductUseCase.existProductBySizeId(existProductCommand);

        assertThat(hasProductBySizeId).isTrue();
    }
    @Test
    @DisplayName("상품사이즈 id 기준 상품정보가 존재하는지 확인한다. - 상품 존재하지 않는 경우 false를리턴한다")
    void existProductBySizeIdWithNoSizeId() {

        //given

        createProduct();

        //when
        ExistProductCommand existProductCommand = ExistProductCommand.builder()
                .sizeId(0)
                .build();

        //then

        boolean hasProductBySizeId = findProductUseCase.existProductBySizeId(existProductCommand);

        assertThat(hasProductBySizeId).isFalse();
    }
    @Test
    @DisplayName("상품 id List를 통해 기준 상품정보를 찾는다.")
    void findProductByProductIds() {
        List<Long> ids = new ArrayList<>();
        //given
        for(int i = 1; i <= 8 ;i++){
            ProductEntity productEntity = ProductEntity.builder()
                    .name("product"+i)
                    .price(i*1000)
                    .productImage("image" + i)
                    .description("content" + i)
                    .build();
            ProductEntity saveProduct = springDataProductRepository.save(productEntity);

            ids.add(saveProduct.getId());
        }
        //when
        FindProductByProductIdsCommand command = FindProductByProductIdsCommand.builder()
                .productIds(ids)
                .build();

        List<ProductVo> findProducts = findProductUseCase.findProductByProductIds(command);
        //then
        assertThat(findProducts).hasSize(8)
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


    private SizeEntity createProduct() {
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
                .productComponent(productComponent)
                .build();

        SizeEntity saveEntity = sizeEntityRepository.save(sizeEntity);

        productComponent.addSize(saveEntity);

        return saveEntity;
    }

}