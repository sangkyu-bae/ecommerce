package com.example.adminservice.application.port.in.usecase;

import com.example.adminservice.application.port.in.command.OrderToUpdateProductCommand;
import com.example.adminservice.application.port.in.command.UpdateProductCommand;
import com.example.adminservice.application.port.in.command.UpdateProductQuantityCommand;
import com.example.adminservice.domain.ProductVo;

public interface UpdateProductUseCase {
    ProductVo updateProduct(UpdateProductCommand command);

    boolean updateProductQuantity(UpdateProductQuantityCommand command);

    void updateProductQuantity (OrderToUpdateProductCommand command);
}
