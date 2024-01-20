package com.example.adminservice.adapter.in.web;

import com.example.adminservice.adapter.in.request.UpdateProductRequest;
import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.application.port.in.usecase.FindProductUseCase;
import com.example.adminservice.application.port.in.usecase.UpdateProductUseCase;
import com.example.adminservice.application.port.in.command.*;

import com.example.adminservice.domain.ProductVo;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import com.example.adminservice.vaildator.UpdateProductCommandValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@WebAdapter
@RequiredArgsConstructor
@RestController
@Slf4j
public class UpdateProductController {
    private final ProductMapper productMapper;
    private final UpdateProductUseCase updateProductUseCase;
    private final UpdateProductCommandValidator validator;
    private final FindProductUseCase findProductUseCase;

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

    @Operation(summary = "update product quantity",description = "상품 컬러별 수량 변경(상품 주문)")
    @PutMapping("/admin/{productId}/{colorId}/{amount}")
    public ResponseEntity<Boolean> updateProductQuantity(
            @RequestParam("productId") long productId,
            @RequestParam("colorId") long colorId,
            @RequestParam("amount") int amount
    ){
        UpdateProductQuantityCommand command = UpdateProductQuantityCommand.builder()
                .productId(productId)
                .colorId(colorId)
                .amount(amount)
                .build();
        command.validateSelf();

        boolean isUpdate = updateProductUseCase.updateProductQuantity(command);

        return ResponseEntity.ok().body(isUpdate);
    }


}
