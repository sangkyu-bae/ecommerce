package com.example.adminservice.application.port.in.usecase.product;

import com.example.adminservice.application.port.in.command.*;
import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;

import java.util.List;

public interface FindProductUseCase {

    ProductVo findProduct(FindProductCommand command);

    ProductSearchVo findPagingProduct(FindPagingProductCommand command);

    ProductSearchVo findPagingProductByCategory(FindPagingProductByCategoryCommand command);
    boolean existProductBySizeId(ExistProductCommand command);

    List<ProductVo> findProductAll();

    List<ProductVo> findProductByProductIds(FindProductByProductIdsCommand command);
}
