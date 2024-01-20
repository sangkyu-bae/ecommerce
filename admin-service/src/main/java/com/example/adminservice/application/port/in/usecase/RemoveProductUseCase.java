package com.example.adminservice.application.port.in.usecase;

import com.example.adminservice.application.port.in.command.DeleteProductCommand;

public interface RemoveProductUseCase {

    boolean removeProduct(DeleteProductCommand command);
}
