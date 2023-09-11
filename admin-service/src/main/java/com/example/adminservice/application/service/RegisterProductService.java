package com.example.adminservice.application.service;

import com.example.adminservice.application.port.in.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.domain.product.dto.ProductVo;

public class RegisterProductService implements RegisterProductUseCase {
    @Override
    public ProductVo registerProduct(RegisterProductCommand command) {

        return null;
    }
}
