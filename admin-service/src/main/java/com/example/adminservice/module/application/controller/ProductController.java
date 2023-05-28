package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.ProductUseCase;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductUseCase productUseCase;

    @PostMapping("/admin/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto createProductDto, Errors errors) throws DataFormatException {
        if (errors.hasErrors()) {
            log.error("product 입력이 잘못되었습니다.");
            ResponseEntity.badRequest().build();
        }

        ProductDto productDto = null;

        try {
            productDto = productUseCase.createMemberExecute(createProductDto);
        } catch (Exception exception) {
            log.error("상품 등록이 실패하였습니다.");
            throw new DataFormatException("create Fail createProduct()");
        }

        return ResponseEntity.ok().body(productDto);
    }
}
