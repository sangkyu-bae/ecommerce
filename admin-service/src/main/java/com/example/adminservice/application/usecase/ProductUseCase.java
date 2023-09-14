package com.example.adminservice.application.usecase;

import com.example.adminservice.domain.product.OrderDto;
import com.example.adminservice.domain.product.dto.CreateProductDto;
import com.example.adminservice.domain.product.dto.ProductDto;
import com.example.adminservice.domain.product.dto.ProductSearchDto;
import com.example.adminservice.domain.product.dto.ResponseProductDto;
import com.example.adminservice.domain.product.entity.Product;
import com.example.adminservice.domain.product.service.ColorProductReadService;
import com.example.adminservice.domain.product.service.ProductReadService;
import com.example.adminservice.domain.product.service.ProductWriteService;
import com.example.adminservice.domain.quantity.entity.Quantity;
import com.example.adminservice.domain.quantity.service.QuantityReadService;
import com.example.adminservice.domain.quantity.service.QuantityWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUseCase {

    private final ProductWriteService productWriteService;
    private final ProductReadService productReadService;
    private final ColorProductReadService colorProductReadService;

    private final QuantityReadService quantityReadService;
    private final QuantityWriteService quantityWriteService;


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

    public OrderDto checkQuantityAndOrderProduct(OrderDto orderDto){
        Quantity quantity = quantityReadService.read(orderDto.getQuantityId());
        quantityWriteService.buyProductQuantity(orderDto,quantity);
        return orderDto;
    }

}
