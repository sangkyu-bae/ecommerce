package com.example.adminservice.application.port.in;

import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.domain.product.dto.ProductVo;


public interface RegisterProductUseCase {

    ProductVo registerProduct(RegisterProductCommand command);
}
