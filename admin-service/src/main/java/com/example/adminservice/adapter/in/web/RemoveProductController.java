package com.example.adminservice.adapter.in.web;

import com.example.adminservice.application.port.in.RemoveProductUseCase;
import com.example.adminservice.application.port.in.product.DeleteProductCommand;
import com.example.adminservice.common.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Operation(summary = "remove product", description = "상품 삭제하기")
    @DeleteMapping("/admin/delete/{productId}")
    public ResponseEntity<Boolean> removeProduct(@PathVariable("productId") final long productId){

        DeleteProductCommand command = DeleteProductCommand.builder()
                .productId(productId)
                .build();

        boolean isRemove = removeProductUseCase.removeProduct(command);

        return ResponseEntity.ok().body(isRemove);
    }
}
