package com.example.adminservice.adapter.in.web;

import com.example.adminservice.application.port.in.FindProductUseCase;
import com.example.adminservice.application.port.in.product.FindPagingProductCommand;
import com.example.adminservice.application.port.in.product.FindProductCommand;


import com.example.adminservice.domain.productentity.ProductSearchVo;
import com.example.adminservice.domain.productentity.ProductVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
