package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.FindProductUseCase;
import com.example.adminservice.application.port.in.product.FindPagingProductCommand;
import com.example.adminservice.application.port.in.product.FindProductCommand;
import com.example.adminservice.application.port.out.FindProductPort;
import com.example.adminservice.common.UseCase;
import com.example.adminservice.domain.product.repository.ColorProductRepository;
import com.example.adminservice.domain.productentity.ProductSearchVo;
import com.example.adminservice.domain.productentity.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class FindProductService implements FindProductUseCase {
    private final FindProductPort findProductPort;
    private final ProductMapper productMapper;
    @Override
    public ProductVo findProduct(FindProductCommand command) {
        ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
        ProductEntity findProduct =  findProductPort.findProduct(productId);
        return productMapper.mapToDomainEntity(findProduct);
    }

    @Override
    public ProductSearchVo findPagingProduct(FindPagingProductCommand command) {
        ProductSearchVo.PageNumber pageNumber = new ProductSearchVo.PageNumber(command.getPageNum());
        Pageable pageable = PageRequest.of(pageNumber.getPageNumber() - 1,6, Sort.Direction.ASC,"id" );
        Page<ProductEntity> findPagingProduct = findProductPort.findPagingProduct(pageable);

        return productMapper.mapToDomainEntity(findPagingProduct);
    }
}
