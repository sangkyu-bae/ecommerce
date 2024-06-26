package org.example.basket.application.port.out;

import org.example.basket.adapter.out.service.Product;

import java.util.List;

public interface GetProductPort {

    boolean getProduct(long sizeId);

    List<Product> getProductListByProductIds(List<Long> productIds);
}
