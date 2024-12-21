package org.example.application.port.out;

import org.example.adapter.out.service.product.Product;

import java.util.List;

public interface GetProductPort {
    List<String> getProductName(List<Long> productIds);
}
