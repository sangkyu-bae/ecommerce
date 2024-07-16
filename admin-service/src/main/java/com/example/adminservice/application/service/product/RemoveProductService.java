package com.example.adminservice.application.service.product;

import com.example.adminservice.application.port.in.usecase.product.RemoveProductUseCase;
import com.example.adminservice.application.port.in.command.DeleteProductCommand;
import com.example.adminservice.application.port.out.brand.RemoveProductPort;

import com.example.adminservice.domain.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class RemoveProductService implements RemoveProductUseCase {

    private final RemoveProductPort removeProductPort;
    @Override
    public boolean removeProduct(DeleteProductCommand command) {
        ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
        return removeProductPort.removeProduct(productId);
    }
}
