package com.example.adminservice.application.port.in.usecase.product;

import com.example.adminservice.application.port.in.command.ExistProductCommand;
import com.example.adminservice.application.port.in.command.FindPagingProductCommand;
import com.example.adminservice.application.port.in.command.FindProductByProductIdsCommand;
import com.example.adminservice.application.port.in.command.FindProductCommand;
import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;

import java.util.List;

public interface FindProductUseCase {

    ProductVo findProduct(FindProductCommand command);

    ProductSearchVo findPagingProduct(FindPagingProductCommand command);

    boolean existProductBySizeId(ExistProductCommand command);

    List<ProductVo> findProductAll();

    List<ProductVo> findProductByProductIds(FindProductByProductIdsCommand command);
}
