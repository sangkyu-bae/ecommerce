package com.example.adminservice.application.port.in;

import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.domain.ProductVo;


public interface RegisterProductUseCase {

    ProductVo registerProduct(RegisterProductCommand command);
}
