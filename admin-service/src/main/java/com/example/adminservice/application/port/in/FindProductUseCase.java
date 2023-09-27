package com.example.adminservice.application.port.in;

import com.example.adminservice.application.port.in.product.FindProductCommand;
import com.example.adminservice.domain.product.dto.ProductVo;

public interface FindProductUseCase {

    ProductVo findProduct(FindProductCommand command);
}
