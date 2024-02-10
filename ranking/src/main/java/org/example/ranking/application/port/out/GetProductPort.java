package org.example.ranking.application.port.out;

import org.example.ranking.adapter.out.service.Product;

import java.util.List;

public interface GetProductPort {
    List<Product> getProductAll();
}
