package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.domain.product.OrderDto;
import com.example.adminservice.module.domain.product.dto.CreateProductDto;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.dto.ProductSearchDto;
import com.example.adminservice.module.domain.product.dto.ResponseProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.service.ProductReadService;
import com.example.adminservice.module.domain.product.service.ProductWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUseCase {

    private final ProductWriteService productWriteService;
    private final ProductReadService productReadService;

    public ResponseProductDto createProductExecute(CreateProductDto productDto) {
        Product product = productWriteService.createProduct(productDto);
        ResponseProductDto toProductDto= productReadService.toProductDtos(product);

        return toProductDto;
    }
    public ResponseProductDto readProduct(long productId){
        Product product = productReadService.readProduct(productId);
        ResponseProductDto toProductDto= productReadService.toProductDtos(product);

        return toProductDto;
    }


    public ProductDto updateProduct(Product product, CreateProductDto updateProductDto){
        product = productWriteService.updateProduct(product,updateProductDto);
        ProductDto productDto = productReadService.toProductDto(product);

        return productDto;
    }

    public ProductSearchDto readProductWithPaging(Pageable pageable){
        Page<Product> productPage = productReadService.readProduct(pageable);
        ProductSearchDto productSearchDto = productReadService.readProductSearch(productPage);

        return productSearchDto;
    }

    public OrderDto createOrder(OrderDto orderDto){

        return orderDto;
    }
}
