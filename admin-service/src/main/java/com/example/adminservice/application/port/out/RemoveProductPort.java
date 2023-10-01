package com.example.adminservice.application.port.out;

import com.example.adminservice.domain.productentity.ProductVo;

public interface RemoveProductPort {

    boolean removeProduct(ProductVo.ProductId productId);
}
