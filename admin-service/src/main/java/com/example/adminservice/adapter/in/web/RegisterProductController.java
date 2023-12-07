package com.example.adminservice.adapter.in.web;

import com.example.adminservice.adapter.in.request.RegisterProductRequest;
import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.application.port.in.product.RegisterBrandCommand;
import com.example.adminservice.application.port.in.product.RegisterCategoryCommand;
import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.application.port.in.product.RegisterProductComponentCommand;
import com.example.adminservice.domain.ProductVo;


import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import com.example.adminservice.vaildator.RegisterProductCommandValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;
    private final ProductMapper productMapper;
    private final RegisterProductCommandValidator validator;
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
}
