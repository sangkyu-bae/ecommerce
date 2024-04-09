package com.example.adminservice.application.port.in.usecase.product;

import com.example.adminservice.application.port.in.command.DeleteProductCommand;

public interface RemoveProductUseCase {

    boolean removeProduct(DeleteProductCommand command);
}
