package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UnknownFormatConversionException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductReadService {
    private final ProductRepository productRepository;


    public ProductDto toProductDto(Product product) {
        ProductDto productDto = null;
        try {
            productDto = new ProductDto(product);
        } catch (Exception exception) {
            log.error("ProductDto 변환 실패하였습니다. Error toProductDto()");
            throw new UnknownFormatConversionException("could Not Convert Member to toProductDto");
        }

        return productDto;
    }

    public Product readProduct(long productId)  {
        Product product = null;
        product = productRepository.findById(productId).orElseThrow(() ->
                new CustomException(ErrorCode.PRODUCT_NOT_FOUND,"readProduct")
        );

        return product;
    }

    public Page<Product> readProduct(Pageable pageable){
        Page<Product> productPage = productRepository.findWithPageByAll(pageable);
        return productPage;
    }

}
