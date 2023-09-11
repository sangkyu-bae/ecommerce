package com.example.adminservice.adapter.in.web;

import com.example.adminservice.application.port.in.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/admin/register/product")
    void registerProduct(@RequestBody RegisterProductRequest registerProductRequest){

        RegisterProductCommand command = RegisterProductCommand.builder()
                .colorDataList(registerProductRequest.colorDataList)
                .brand(registerProductRequest.getBrand())
                .category(registerProductRequest.getCategory())
                .name(registerProductRequest.getName())
                .productImage(registerProductRequest.getProductImage())
                .description(registerProductRequest.getDescription())
                .price(registerProductRequest.getPrice())
                .build();

        registerProductUseCase.registerProduct(command);

    }
}
