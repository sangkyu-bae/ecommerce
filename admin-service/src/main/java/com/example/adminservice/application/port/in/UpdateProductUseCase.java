package com.example.adminservice.application.port.in;

import com.example.adminservice.application.port.in.product.UpdateProductCommand;
import com.example.adminservice.domain.productentity.ProductVo;

public interface UpdateProductUseCase {
    ProductVo updateProduct(UpdateProductCommand command);
}
