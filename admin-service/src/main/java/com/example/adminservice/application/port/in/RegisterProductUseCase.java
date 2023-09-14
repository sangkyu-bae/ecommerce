package com.example.adminservice.application.port.in;

import com.example.adminservice.common.UseCase;
import com.example.adminservice.domain.product.dto.ProductVo;


public interface RegisterProductUseCase {

    ProductVo registerProduct(RegisterProductCommand command);
}
