package com.example.order.application.port.out;

import com.example.order.adapter.out.service.Product;

import java.util.List;

public interface GetProductPort {
    Product getProduct(long productId);

    List<Product> getProductListByProductIds(List<Long> productIds);
}

