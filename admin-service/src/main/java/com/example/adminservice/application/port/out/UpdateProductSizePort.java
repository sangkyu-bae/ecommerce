package com.example.adminservice.application.port.out;

import com.example.adminservice.domain.productentity.ProductVo;
import com.example.adminservice.domain.productentity.SizeVo;

public interface UpdateProductSizePort {

    void updateProductSize(SizeVo sizeVo);
}
