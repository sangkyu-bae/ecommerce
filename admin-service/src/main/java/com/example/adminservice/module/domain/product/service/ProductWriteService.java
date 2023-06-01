package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductWriteService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Product createProduct(ProductDto productDto) {
        Product product = saveNewProduct(productDto);
        return product;
    }

    private Product saveNewProduct(ProductDto productDto){
        Product product = modelMapper.map(productDto,Product.class);
        product.setCreateAt(LocalDate.now());
        product.setUpdateAt(LocalDate.now());

        return  productRepository.save(product);
    }

    public void removeProduct(long productId){
        try{
           productRepository.deleteById(productId);
        }catch (Exception e){
            log.error("상품이 삭제되지 않았습니다 removeProduct()");
        }
    }

}
