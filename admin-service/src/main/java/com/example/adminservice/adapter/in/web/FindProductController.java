package com.example.adminservice.adapter.in.web;

import com.example.adminservice.application.port.in.command.ExistProductCommand;
import com.example.adminservice.application.port.in.usecase.FindProductUseCase;
import com.example.adminservice.application.port.in.command.FindPagingProductCommand;
import com.example.adminservice.application.port.in.command.FindProductCommand;


import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

        FindPagingProductCommand command = FindPagingProductCommand.builder()
                .pageNum(pageNum)
                .build();

        ProductSearchVo productSearchVo = findProductUseCase.findPagingProduct(command);

        return ResponseEntity.ok().body(productSearchVo);
    }


}
