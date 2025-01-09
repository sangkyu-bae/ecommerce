package com.example.adminservice.application.service.product;

import com.example.adminservice.adapter.out.kafka.ProductProducer;
import com.example.adminservice.adapter.out.persistence.entity.*;
import com.example.adminservice.adapter.out.persistence.repository.*;
import com.example.adminservice.application.port.in.command.RegisterBrandCommand;
import com.example.adminservice.application.port.in.command.RegisterCategoryCommand;
import com.example.adminservice.application.port.in.command.UpdateProductCommand;
import com.example.adminservice.application.port.in.command.UpdateProductQuantityCommand;
import com.example.adminservice.application.port.in.usecase.product.FindProductUseCase;
import com.example.adminservice.application.port.in.usecase.product.UpdateProductUseCase;
import com.example.adminservice.application.port.out.brand.UpdateProductSizePort;
import com.example.adminservice.domain.ProductVo;
import com.example.adminservice.domain.SizeVo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UpdateProductServiceTest {
    @Autowired
    private UpdateProductUseCase updateProductUseCase;

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

    @Autowired
    private BrandEntityRepository brandEntityRepository;



    @AfterEach
    void tearDown(){
        productComponentEntityRepository.deleteAll();
        springDataProductRepository.deleteAll();
        categoryEntityRepository.deleteAll();
        sizeEntityRepository.deleteAll();
        colorEntityRepository.deleteAll();
    }


    @Test
    @DisplayName("상품의 정보를 수정한다.")
    void updateProduct() {


        String description = "updateDescription";
        String name = "updateName";
        int price = 100;
        String productImage = "updateImage";

        //given
        ProductEntity createProduct = createProduct();

        UpdateProductCommand command = UpdateProductCommand.updateProductCommandBuilder()
                .id(createProduct.getId())
                .description(description)
                .name(name)
                .price(price)
                .productImage(productImage)
                .productComponents(new HashSet<>())
                .brand(new RegisterBrandCommand(createProduct.getBrand().getId(), createProduct.getBrand().getName()))
                .category(new RegisterCategoryCommand(createProduct.getCategory().getId(),createProduct.getCategory().getName()))
                .build();
        //when
        ProductVo productVo = updateProductUseCase.updateProduct(command);
        //then
        assertThat(productVo).extracting("name","price","description","productImage")
                .contains(name,price,description,productImage);
    }

    @Test
    @DisplayName("상품의 컬러 사이즈의 수량을 변경한다.")
    void updateProductQuantity() {

        //given
        SizeEntity create = create(260,50);

        //when
        UpdateProductQuantityCommand command = UpdateProductQuantityCommand.builder()
                .sizeId(create.getId())
                .amount(5)
                .build();
        boolean isUpdate = updateProductUseCase.updateProductQuantity(command);
        //then
        assertThat(isUpdate).isTrue();
    }

    private SizeEntity create(int size,int quantity){

        SizeEntity sizeEntity = SizeEntity.builder()
                .size(size)
                .quantity(quantity)
                .build();

        return sizeEntityRepository.save(sizeEntity);
    }

    private ProductEntity createProduct() {
        ColorEntity colorEntity = ColorEntity.builder()
                .name("red")
                .build();

        ColorEntity saveColor =  colorEntityRepository.save(colorEntity);

        BrandEntity brandEntity = BrandEntity.builder()
                .name("nike")
                .build();
        BrandEntity createBrand = brandEntityRepository.save(brandEntity);

        CategoryEntity category = CategoryEntity.builder()
                .name("운동화")
                .build();

        CategoryEntity createCategory = categoryEntityRepository.save(category);


        ProductEntity productEntity = ProductEntity.builder()
                .name("product")
                .price(1000)
                .productImage("image")
                .description("content")
                .brand(createBrand)
                .category(category)
                .build();
        ProductEntity createProduct = springDataProductRepository.save(productEntity);

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

        createProduct.addProductComponent(productComponent);

        return createProduct;
    }

}