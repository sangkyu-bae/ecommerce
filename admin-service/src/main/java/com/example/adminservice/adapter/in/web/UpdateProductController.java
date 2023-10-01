package com.example.adminservice.adapter.in.web;

import com.example.adminservice.adapter.in.web.request.productRequest.UpdateProductRequest;
import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.application.port.in.UpdateProductUseCase;
import com.example.adminservice.application.port.in.product.*;
import com.example.adminservice.common.WebAdapter;
import com.example.adminservice.domain.productentity.ProductVo;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.ProductErrorCode;
import com.example.adminservice.vaildator.UpdateProductCommandValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@WebAdapter
@RequiredArgsConstructor
@RestController
@Slf4j
public class UpdateProductController {
    private final ProductMapper productMapper;
    private final UpdateProductUseCase updateProductUseCase;

    private final UpdateProductCommandValidator validator;

    @Operation(summary = "update product", description = "상품 수정하기")
    @PutMapping("/admin/update/{productId}")
    public ResponseEntity<ProductVo> updateProduct(@PathVariable ("productId") long productId,
                                                   @RequestBody UpdateProductRequest updateProductRequest){
        String productImage = updateProductRequest.getProductImage();
        productImage = productImage == null ? "" : productImage;
        updateProductRequest.setProductImage(productImage);

        RegisterBrandCommand registerBrandCommand = productMapper.mapToBrand(updateProductRequest.getBrand());
        RegisterCategoryCommand registerCategoryCommand = productMapper.mapToCategory(updateProductRequest.getCategory());
        Set<RegisterProductComponentCommand> componentCommands = productMapper.mapToCommandProductComponents(updateProductRequest.getProductComponents());

        UpdateProductCommand command = UpdateProductCommand.updateProductCommandBuilder()
                .id(productId)
                .description(updateProductRequest.getDescription())
                .name(updateProductRequest.getName())
                .price(updateProductRequest.getPrice())
                .productImage(updateProductRequest.getProductImage())
                .productComponents(componentCommands)
                .brand(registerBrandCommand)
                .category(registerCategoryCommand)
                .build();

        Errors errors = new BeanPropertyBindingResult(command, "command");
        validator.validate(command, errors);

        if (errors.hasErrors()) {
            throw new ErrorException(ProductErrorCode.PRODUCT_FORM_NO_VALIDATE,"updateProduct");
        }

        ProductVo updateProductVo = updateProductUseCase.updateProduct(command);

        return ResponseEntity.ok().body(updateProductVo);
    }
}
