package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.QuantityUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuantityController {

    private final QuantityUseCase quantityUseCase;

    @GetMapping("/admin/quantity/{quantityId}/{buyQuantity}")
    public ResponseEntity<Integer> updateBuyProductQuantity(@PathVariable("quantityId") Long quantityId,
                                                            @PathVariable("buyQuantity") int buyQuantity){

        int quantity = quantityUseCase.updateQuantity(quantityId,buyQuantity);
        return ResponseEntity.ok().body(quantity);
    }
}
