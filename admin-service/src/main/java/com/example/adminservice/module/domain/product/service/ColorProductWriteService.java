package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.color.repository.ColorRepository;
import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ColorProductRepository;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorProductWriteService {

    private final ColorProductRepository colorProductRepository;

    public ColorProduct createColorProduct(ColorProduct colorProduct,SizeQuantity sizeQuantity ,Product product){
        colorProduct.setProduct(product);
//        colorProduct.setSizeList(List.of(sizeQuantity));
        return colorProductRepository.save(colorProduct);
    }
}
