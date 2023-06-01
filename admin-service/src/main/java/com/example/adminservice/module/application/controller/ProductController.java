package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.ProductUseCase;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.service.ProductWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductUseCase productUseCase;

    private final ProductWriteService productWriteService;

    @PostMapping("/admin/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto createProductDto, Errors errors) throws DataFormatException {
        if (errors.hasErrors()) {
            log.error("product 입력이 잘못되었습니다.");
            return ResponseEntity.badRequest().build();
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

    @DeleteMapping("/admin/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable("productId") long productId){
        try{
            productWriteService.removeProduct(productId);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("상품이 삭제되지 않았습니다.");
        }

        return ResponseEntity.ok().body("상품이 삭제 되었습니다.");
    }
}
