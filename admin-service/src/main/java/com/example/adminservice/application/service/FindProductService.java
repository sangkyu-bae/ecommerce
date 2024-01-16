package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.FindProductUseCase;
import com.example.adminservice.application.port.in.product.FindPagingProductCommand;
import com.example.adminservice.application.port.in.product.FindProductCommand;
import com.example.adminservice.application.port.out.FindProductPort;

import com.example.adminservice.application.port.out.SendFindProductTaskPort;
import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class FindProductService implements FindProductUseCase {
    private final FindProductPort findProductPort;
    private final ProductMapper productMapper;

    private final SendFindProductTaskPort sendFindProductTaskPort;

    @Override
    public ProductVo findProduct(FindProductCommand command) {
        ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
        ProductEntity findProduct =  findProductPort.findProduct(productId);

        sendFindProductTaskPort.sendFindProductTask(productId.getId());
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
