package com.example.adminservice.domain.product.service;

import com.example.adminservice.domain.product.entity.ColorProduct;
import com.example.adminservice.domain.product.entity.Product;
import com.example.adminservice.domain.product.repository.ColorProductRepository;
import com.example.adminservice.domain.size.entity.SizeQuantity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorProductWriteService {

    private final ColorProductRepository colorProductRepository;

    public ColorProduct createColorProduct(ColorProduct colorProduct, SizeQuantity sizeQuantity , Product product){
        colorProduct.setProduct(product);
//        colorProduct.setSizeList(List.of(sizeQuantity));
        return colorProductRepository.save(colorProduct);
    }
}
