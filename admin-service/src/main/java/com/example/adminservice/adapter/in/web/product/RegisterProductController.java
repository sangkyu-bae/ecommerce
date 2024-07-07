package com.example.adminservice.adapter.in.web.product;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;
import com.example.adminservice.adapter.in.request.RegisterProductRequest;
import com.example.adminservice.adapter.out.persistence.entity.*;
import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.repository.*;
import com.example.adminservice.application.port.in.command.RegisterBrandCommand;
import com.example.adminservice.application.port.in.command.RegisterCategoryCommand;
import com.example.adminservice.application.port.in.command.RegisterProductCommand;
import com.example.adminservice.application.port.in.usecase.product.RegisterProductUseCase;
import com.example.adminservice.application.port.in.command.RegisterProductComponentCommand;
import com.example.adminservice.domain.Category;
import com.example.adminservice.domain.ProductVo;


import com.example.adminservice.domain.SizeCheck;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import com.example.adminservice.vaildator.RegisterProductCommandValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;
    private final ProductMapper productMapper;
    private final RegisterProductCommandValidator validator;
    private final SpringDataProductRepository productRepository;
    private final ColorEntityRepository colorEntityRepository;

    private final BrandEntityRepository brandEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final CommandGateway commandGateway;

    @Operation(summary = "register product", description = "상품 등록하기")
    @PostMapping("/admin/register/product")
    public ResponseEntity<ProductVo> registerProduct(@RequestBody RegisterProductRequest registerProductRequest) {
        String productImage = registerProductRequest.getProductImage();
        productImage = productImage == null ? "" : productImage;
        registerProductRequest.setProductImage(productImage);

        RegisterBrandCommand registerBrandCommand = productMapper.mapToBrand(registerProductRequest.getBrand());
        RegisterCategoryCommand registerCategoryCommand = productMapper.mapToCategory(registerProductRequest.getCategory());
        Set<RegisterProductComponentCommand> componentCommands = productMapper.mapToCommandProductComponents(registerProductRequest.getProductComponents());

        RegisterProductCommand command = RegisterProductCommand.builder()
                .brand(registerBrandCommand)
                .category(registerCategoryCommand)
                .productComponents(componentCommands)
                .name(registerProductRequest.getName())
                .price(registerProductRequest.getPrice())
                .description(registerProductRequest.getDescription())
                .productImage(productImage)
                .build();

        Errors errors = new BeanPropertyBindingResult(command, "command");
        validator.validate(command, errors);

        if (errors.hasErrors()) {
            throw new ErrorException(ProductErrorCode.PRODUCT_FORM_NO_VALIDATE,"registerProduct");
        }

        ProductVo productVo = registerProductUseCase.registerProduct(command);

        return ResponseEntity.ok().body(productVo);
    }

    /**
     * todo : bulk insert로 성능 개선 기본키 전략에 의해 쓰기 지연 안됨 (mysql시)
     * */
    @Operation(summary = "register product", description = "test 상품 등록하기")
    @PostMapping("/admin/register-test-bulk")
    public void registerProductBulkTest(){

        List<ProductEntity> productEntities = new ArrayList<>();

        List<ColorEntity> colorEntityList = colorEntityRepository.findAll();
        int colorSize = colorEntityList.size();
        Random colorRandom = new Random();

        List<BrandEntity> brandEntityList = brandEntityRepository.findAll();
        int brandSize = brandEntityList.size();
        Random brandRandom = new Random();

        List<CategoryEntity> categoryList = categoryEntityRepository.findAll();
        int categorySize = categoryList.size();
        Random categoryRandom = new Random();

        Random quantityRandom = new Random();


        for(int i = 1 ; i <= 10000; i++){
            String name = "testProduct : " + i;
            int price = i * 1000;
            String description = "testProduct Description : " + i;
            String aggregateIdentifier = UUID.randomUUID().toString();
            int brandRandomIndex = brandRandom.nextInt(brandSize);
            int categoryRandomIndex = categoryRandom.nextInt(categorySize);

            ProductEntity productEntity = ProductEntity.builder()
                    .name(name)
                    .price(price)
                    .description(description)
                    .aggregateIdentifier(aggregateIdentifier)
                    .brand(brandEntityList.get(brandRandomIndex))
                    .category(categoryList.get(categoryRandomIndex))
                    .build();
            List<ProductComponentEntity> productComponentEntities = new ArrayList<>();

            for(int j = 0 ;  j < colorSize ; j++){
                ProductComponentEntity productComponent = ProductComponentEntity.builder()
                        .color(colorEntityList.get(j))
                        .product(productEntity)
                        .build();

                List<SizeEntity> sizeEntities = new ArrayList<>();
                List<Integer> sizeList = SizeCheck.getSize();

                for(int z = 0 ; z < sizeList.size(); z++){
                    SizeEntity sizeEntity = SizeEntity.builder()
                            .size(sizeList.get(z))
                            .quantity(quantityRandom.nextInt(10000))
                            .productComponent(productComponent)
                            .build();

                    sizeEntities.add(sizeEntity);
                }

                Set<SizeEntity> sizeEntitySet = new HashSet<>(sizeEntities);
                productComponent.setSizes(sizeEntitySet);
                productComponentEntities.add(productComponent);

            }

            Set<ProductComponentEntity> productComponentEntitySet = new HashSet<>(productComponentEntities);
            productEntity.setProductComponents(productComponentEntitySet);

            ProductCreateCommand axonCommand = ProductCreateCommand.builder()
                    .productImage(productEntity.getProductImage())
                    .price(productEntity.getPrice())
                    .description(productEntity.getDescription())
                    .name(productEntity.getName())
                    .aggregateIdentifier(productEntity.getAggregateIdentifier())
                    .build();
            commandGateway.send(axonCommand).whenComplete((result, throwable)->{
                if(throwable != null){
                    log.error("throwable = " + throwable);
                    throw new RuntimeException(throwable);
                }else{
                    log.info("result = " + result);
                }
            });
            productEntities.add(productEntity);
        }

        productRepository.saveAll(productEntities);

    }
}
