package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.ProductUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.service.ProductWriteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductWriteService productWriteService;

    /**
     * 상품 등록하기
     * @Params ProductDto(등록할 상품 정보)
     * @return ProductDto
     * */
    @PostMapping("/admin/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto createProductDto,
                @RequestHeader("X-User-Id") String userId ,Errors errors) throws DataFormatException {
        if (errors.hasErrors()) {
            throw new CustomException(ErrorCode.PRODUCT_FORM_NO_VALIAD,"createProduct");
        }
        ProductDto productDto = productUseCase.createProductExecute(createProductDto);
        log.info("{}가 {} 상품을 등록 하였습니다",userId, productDto.getName());
        return ResponseEntity.ok().body(productDto);
    }

    /**
     * 상품 삭제 하기
     * @Params productId(상품 고유 Id)
     * */
    @DeleteMapping("/admin/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable("productId") long productId, @RequestHeader("X-User-Id") String userId){
        productWriteService.removeProduct(productId);
        log.info("{}가 상품을 삭제 하였습니다.",userId);
        return ResponseEntity.ok().body("상품이 삭제 되었습니다.");
    }

    /**
     * 특정 상품 가져오기
     * @Params productId(상품 고유 Id)
     * @return ProductDto
     * */
    @GetMapping("/admin/{productId}")
    public ResponseEntity<ProductDto> readProduct(@PathVariable("productId") long productId, @RequestHeader("X-User-Id") String userId){
        ProductDto productDto = productUseCase.readProduct(productId);
        log.info("{}가 {} 상품을 조회하였습니다", userId, productDto.getName());
        return ResponseEntity.ok().body(productDto);
    }
    /**
     * 상품 수정
     * @param productId (상품 고유 Id) updateProductDto (수정 상품 정보)
     * @return ProductDto
     * */
    @PutMapping("/admin/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") long productId,
                                                    @Valid @RequestBody ProductDto updateProductDto, Errors errors,
                                                    @RequestHeader("X-User-Id") String userId){
        if (errors.hasErrors()) {
            throw new CustomException(ErrorCode.PRODUCT_FORM_NO_VALIAD,"updateProduct");
        }
        ProductDto productDto = productUseCase.updateProduct(productId,updateProductDto);
        log.info("{}가 {} 상품을 수정 하였습니다", userId, productDto.getName());
        return ResponseEntity.ok().body(productDto);
    }

    /**
     * 메인 화면 상품 Paging
     * @return List<Prouct>
     * */
    @GetMapping("/admin")
    public ResponseEntity<List<Product>> readProduct(@PageableDefault(size=9,sort = "id",direction = Sort.Direction.ASC) Pageable pageable){
        Page<Product> productPage = productUseCase.readProductWithPaging(pageable);
        log.info("상품이 조회 되었습니다");
        return ResponseEntity.ok().body(productPage.getContent());
    }
}
