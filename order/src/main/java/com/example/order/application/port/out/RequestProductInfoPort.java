package com.example.order.application.port.out;

import com.example.order.adapter.out.external.product.ProductInfoRequest;

public interface RequestProductInfoPort {
    boolean getProductQuantity(ProductInfoRequest productInfoRequest);
}
