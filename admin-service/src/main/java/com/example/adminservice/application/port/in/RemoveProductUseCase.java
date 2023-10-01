package com.example.adminservice.application.port.in;

import com.example.adminservice.application.port.in.product.DeleteProductCommand;

public interface RemoveProductUseCase {

    boolean removeProduct(DeleteProductCommand command);
}
