package com.example.adminservice.adapter.in.web;

import com.example.adminservice.application.port.in.RemoveProductUseCase;
import com.example.adminservice.application.port.in.product.DeleteProductCommand;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RemoveProductController {

    private final RemoveProductUseCase removeProductUseCase;

    @Operation(summary = "remove product with kafka", description = "카프카로 상품 삭제하기")
    @DeleteMapping("/admin/delete/kafka/{productId}")
    public ResponseEntity<Boolean> removeProduct(@PathVariable("productId") final long productId){

        DeleteProductCommand command = DeleteProductCommand.builder()
                .productId(productId)
                .build();

        boolean isRemove = removeProductUseCase.removeProduct(command);

        return ResponseEntity.ok().body(isRemove);
    }
}
