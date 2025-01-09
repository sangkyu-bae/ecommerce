package com.example.adminservice.application.service.product;

import com.example.adminservice.adapter.out.kafka.ProductProducer;
import com.example.adminservice.adapter.out.persistence.entity.SizeEntity;
import com.example.adminservice.adapter.out.persistence.repository.*;
import com.example.adminservice.application.port.in.command.UpdateProductQuantityCommand;
import com.example.adminservice.application.port.in.usecase.product.FindProductUseCase;
import com.example.adminservice.application.port.in.usecase.product.UpdateProductUseCase;
import com.example.adminservice.application.port.out.brand.UpdateProductSizePort;
import com.example.adminservice.domain.SizeVo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

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

        //given

        //when

        //then
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
}