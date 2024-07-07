package com.example.adminservice.adapter.in.web.product;

import com.example.adminservice.application.port.in.command.ExistProductCommand;
import com.example.adminservice.application.port.in.command.FindProductByProductIdsCommand;
import com.example.adminservice.application.port.in.usecase.product.FindProductUseCase;
import com.example.adminservice.application.port.in.command.FindPagingProductCommand;
import com.example.adminservice.application.port.in.command.FindProductCommand;


import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class FindProductController {
    private final FindProductUseCase findProductUseCase;


    @Operation(summary = "find product", description = "상품 조회하기")
    @GetMapping("/admin/find/{productId}")
    public ResponseEntity<ProductVo> findProduct(@PathVariable("productId") long productId){

        FindProductCommand command = FindProductCommand.builder()
                .productId(productId)
                .build();

        ProductVo productVo = findProductUseCase.findProduct(command);

        return ResponseEntity.ok().body(productVo);
    }

    @Operation(summary = "find product", description = "해당 컬러 및 사이즈 제품 조회하기")
    @GetMapping("/admin/find/product-size/{sizeId}")
    public ResponseEntity<Boolean> existProduct(@PathVariable ("sizeId") long sizeId){

        ExistProductCommand command = ExistProductCommand.builder()
                .sizeId(sizeId)
                .build();


        boolean isExist = findProductUseCase.existProductBySizeId(command);

        return ResponseEntity.ok().body(isExist);
    }



    @Operation(summary = "find paging product", description = "상품 페이징")
    @GetMapping("/admin/page/product/{pageNum}")
    public ResponseEntity<ProductSearchVo> findPagingProduct(@PathVariable("pageNum") int pageNum){
        long startTime = System.currentTimeMillis();

        FindPagingProductCommand command = FindPagingProductCommand.builder()
                .pageNum(pageNum)
                .build();

        ProductSearchVo productSearchVo = findProductUseCase.findPagingProduct(command);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Execution time for findPagingProduct: {} ms", duration);

        return ResponseEntity.ok().body(productSearchVo);
    }

    @Operation(summary = "findAll Product",description = "모든상품조회")
    @GetMapping("/admin")
    public ResponseEntity<List<ProductVo>> findProductAll(){
        List<ProductVo> getAllProduct = findProductUseCase.findProductAll();
        return ResponseEntity.ok().body(getAllProduct);
    }

    @Operation(summary = "find Product By ProductIds", description = "ProductIds로 List 조회하기")
    @PostMapping("/admin/product-list")
    public ResponseEntity<List<ProductVo>> findProductBySizeId(@RequestBody List<Long> productIds){

        FindProductByProductIdsCommand command = FindProductByProductIdsCommand
                .builder()
                .productIds(productIds)
                .build();

        List<ProductVo> findProductList = findProductUseCase.findProductByProductIds(command);

        return ResponseEntity.ok().body(findProductList);

    }
}
