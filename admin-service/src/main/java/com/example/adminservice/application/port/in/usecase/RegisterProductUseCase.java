package com.example.adminservice.application.port.in.usecase;

import com.example.adminservice.application.port.in.command.RegisterProductCommand;
import com.example.adminservice.domain.ProductVo;


public interface RegisterProductUseCase {

    ProductVo registerProduct(RegisterProductCommand command);
}
