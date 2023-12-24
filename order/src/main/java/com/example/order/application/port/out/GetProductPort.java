package com.example.order.application.port.out;

import com.example.order.adapter.out.service.Product;

public interface GetProductPort {
    Product getProduct(long productId);
}

