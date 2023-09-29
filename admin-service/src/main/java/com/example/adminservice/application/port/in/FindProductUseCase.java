package com.example.adminservice.application.port.in;

import com.example.adminservice.application.port.in.product.FindPagingProductCommand;
import com.example.adminservice.application.port.in.product.FindProductCommand;
import com.example.adminservice.domain.productentity.ProductSearchVo;
import com.example.adminservice.domain.productentity.ProductVo;

import java.util.List;

public interface FindProductUseCase {

    ProductVo findProduct(FindProductCommand command);

    ProductSearchVo findPagingProduct(FindPagingProductCommand command);
}
