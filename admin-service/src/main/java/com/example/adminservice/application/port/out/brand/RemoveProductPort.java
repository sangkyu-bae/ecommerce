package com.example.adminservice.application.port.out.brand;

import com.example.adminservice.domain.ProductVo;

public interface RemoveProductPort {

    boolean removeProduct(ProductVo.ProductId productId);
}
