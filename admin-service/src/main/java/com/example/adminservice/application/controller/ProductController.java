package com.example.adminservice.application.controller;

import com.example.adminservice.application.usecase.BrandUseCase;
import com.example.adminservice.application.usecase.CategoryUseCase;
import com.example.adminservice.application.usecase.ColorUseCase;
import com.example.adminservice.application.usecase.ProductUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.module.common.kafka.KafkaService;
import com.example.adminservice.domain.product.OrderDto;
import com.example.adminservice.domain.product.dto.CreateProductDto;
import com.example.adminservice.domain.product.dto.ProductDto;
import com.example.adminservice.domain.product.dto.ProductSearchDto;
import com.example.adminservice.domain.product.dto.ResponseProductDto;
import com.example.adminservice.adapter.out.persistence.Product;
import com.example.adminservice.domain.product.service.ProductReadService;
import com.example.adminservice.domain.product.service.ProductWriteService;
import com.example.adminservice.domain.product.validator.CreateProductDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductWriteService productWriteService;
    private final CategoryUseCase categoryUseCase;
    private final ColorUseCase colorUseCase;
    private final BrandUseCase brandUseCase;
    private final CreateProductDtoValidator createProductDtoValidator;
    private final ProductReadService productReadService;
    private final KafkaService kafkaService;
    @InitBinder("createProductDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(createProductDtoValidator);
    }

    /**
     * 상품 등록하기
     * @Params ProductDto(등록할 상품 정보)
     * @return ProductDto
     * */
    @PostMapping("/admin/product")
    public ResponseEntity<ResponseProductDto> createProduct(@Valid @RequestBody CreateProductDto createProductDto,
                                                            @RequestHeader("X-User-Id") String userId ,Errors errors) {
        if (errors.hasErrors()) {
            throw new CustomException(ErrorCodet.PRODUCT_FORM_NO_VALIDATE,"createProduct");
        }
        ResponseProductDto productDto = productUseCase.createProductExecute(createProductDto);
        log.info("{}가 {} 상품을 등록 하였습니다",userId, productDto.getName());
        return ResponseEntity.ok().body(new ResponseProductDto());
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
    public ResponseEntity<ResponseProductDto> readProduct(@PathVariable("productId") long productId, @RequestHeader("X-User-Id") String userId){
        ResponseProductDto productDto = productUseCase.readProduct(productId);
        log.info("{}가 {} 상품을 조회하였습니다", userId, productDto.getName());
        return ResponseEntity.ok().body(productDto);
    }

    /**
     * 상품 수정
     * @param productId (상품 고유 Id) updateProductDto (수정 상품 정보)
     * @return ProductDto
     * */
    @PutMapping("/admin/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,
//                                                    @PathVariable("productId") Product product,
                                                    @Valid @RequestBody CreateProductDto updateProductDto, Errors errors,
                                                    @RequestHeader("X-User-Id") String userId){
        Product product = productReadService.readProduct(productId);
        if (errors.hasErrors()) {
            throw new CustomException(ErrorCodet.PRODUCT_FORM_NO_VALIDATE,"updateProduct");
        }
        ProductDto productDto = productUseCase.updateProduct(product,updateProductDto);
        log.info("{}가 {} 상품을 수정 하였습니다", userId, productDto.getName());
        return ResponseEntity.ok().body(productDto);
    }

    /**
     * 메인 화면 상품 Paging
     * @return List<Prouct>
     * */
    @GetMapping("/admin/page/{pageNum}")
    public ResponseEntity<ProductSearchDto> readProduct(@PathVariable("pageNum") int pageNum){
        int pageSize = 6;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "id");

        ProductSearchDto productSearchDto = productUseCase.readProductWithPaging(pageable);
        log.info("상품이 조회 되었습니다");
        return ResponseEntity.ok().body(productSearchDto);
    }

    /**
     * 상품 등록을 위한 브랜드, 컬러, 카테고리 가져오기
     * */
    @GetMapping("/admin/product-info")
    public ResponseEntity<Map<String, List<? extends Object>>> readProductInfo(){
        var colorDtoAllList = colorUseCase.readAllColorDtoExecute();
        var brandDtoAllList = brandUseCase.readAllBrandDtoExecute();
        var categoryDtoAllList = categoryUseCase.readAllCategoryDtoExecute();

        Map<String, List<? extends Object>> productInfoMap = Map.of(
                "color",colorDtoAllList,
                "brand",brandDtoAllList,
                "category",categoryDtoAllList
        );

        return ResponseEntity.ok().body(productInfoMap);
    }
    /**
     * 상품재고 확인 및 재고 감소
     * 주문 모듈로 이벤트 전송
     *  */

    @PostMapping("/admin/product-order/{quantityId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable ("quantityId") Long quantityId,
                                @RequestBody OrderDto createOrderDto,
                                @RequestHeader("X-User-Id") String userId){
        createOrderDto.setQuantityId(quantityId);
        createOrderDto.setUserId(userId);
        try{
            productUseCase.checkQuantityAndOrderProduct(createOrderDto);
            kafkaService.sendOrderToKafka(createOrderDto);
        }catch (Exception e){
            log.error("createOrder Error",e);
        }

        return ResponseEntity.ok().body(createOrderDto);
    }


}
