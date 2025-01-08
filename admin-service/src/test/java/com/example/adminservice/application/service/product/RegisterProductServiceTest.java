package com.example.adminservice.application.service.product;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;
import com.example.adminservice.adapter.out.axon.AxonProductProducer;
import com.example.adminservice.adapter.out.kafka.ProductProducer;
import com.example.adminservice.adapter.out.persistence.entity.BrandEntity;
import com.example.adminservice.adapter.out.persistence.entity.CategoryEntity;
import com.example.adminservice.adapter.out.persistence.repository.BrandEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.CategoryEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.SizeEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.SpringDataProductRepository;
import com.example.adminservice.application.port.in.command.RegisterBrandCommand;
import com.example.adminservice.application.port.in.command.RegisterCategoryCommand;
import com.example.adminservice.application.port.in.command.RegisterProductCommand;
import com.example.adminservice.domain.ProductVo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


import java.util.HashSet;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ActiveProfiles("test")
class RegisterProductServiceTest {


    @MockBean
    private AxonProductProducer axonProductProducer;

    @MockBean
    private ProductProducer productProducer;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    @Autowired
    private SizeEntityRepository sizeEntityRepository;

    @Autowired
    private SpringDataProductRepository springDataProductRepository;

    @Autowired
    private BrandEntityRepository brandEntityRepository;

    @Autowired
    private RegisterProductService registerProductService;
    @AfterEach
    void tearDown(){
        springDataProductRepository.deleteAll();
        categoryEntityRepository.deleteAll();
        brandEntityRepository.deleteAll();
        sizeEntityRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 정보를 입력하여 상품을 등록한다.")
    void registerProduct() {

        //given
        CategoryEntity category = CategoryEntity.builder()
                .name("신발")
                .build();

        CategoryEntity saveCategory = categoryEntityRepository.save(category);

        BrandEntity brand = BrandEntity.builder()
                .name("nike")
                .build();

        BrandEntity saveBrand = brandEntityRepository.save(brand);

        String name = "test";
        int price = 1000;
        String description = "testDescriptionTest";
        String productImage = "testImage";
        RegisterProductCommand command = RegisterProductCommand.builder()
                .name(name)
                .price(price)
                .description(description)
                .productImage(productImage)
                .brand(new RegisterBrandCommand(saveBrand.getId(),saveBrand.getName()))
                .category(new RegisterCategoryCommand(saveCategory.getId(),saveCategory.getName()))
                .productComponents(new HashSet<>())
                .build();
        doNothing().when(axonProductProducer).sendProductToAxon(new ProductCreateCommand());
        doNothing().when(productProducer).sendCreateProductTask(anyLong());

        //when
        ProductVo createProduct = registerProductService.registerProduct(command);
        //then

        assertThat(createProduct)
                .extracting("name","price","description","productImage")
                .contains(name,productImage,description,productImage);
    }
}