package org.example.basket.application.port.out;

import org.example.basket.adapter.out.service.Product;

public interface GetProductPort {

    boolean getProduct(long sizeId);
}
