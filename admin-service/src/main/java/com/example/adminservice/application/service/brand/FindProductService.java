package com.example.adminservice.application.service.brand;

import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import com.example.adminservice.application.port.in.command.ExistProductCommand;
import com.example.adminservice.application.port.in.command.FindProductByProductIdsCommand;
import com.example.adminservice.application.port.in.usecase.product.FindProductUseCase;
import com.example.adminservice.application.port.in.command.FindPagingProductCommand;
import com.example.adminservice.application.port.in.command.FindProductCommand;
import com.example.adminservice.application.port.out.brand.FindProductPort;

import com.example.adminservice.application.port.out.brand.SendFindProductTaskPort;
import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
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

    private final SendFindProductTaskPort sendFindProductTaskPort;

    @Override
    public ProductVo findProduct(FindProductCommand command) {
        ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
        ProductEntity findProduct =  findProductPort.findProduct(productId);

        sendFindProductTaskPort.sendFindProductTask(productId.getId(),findProduct.getName());
        return productMapper.mapToDomainEntity(findProduct);
    }



    @Override
    public ProductSearchVo findPagingProduct(FindPagingProductCommand command) {
        ProductSearchVo.PageNumber pageNumber = new ProductSearchVo.PageNumber(command.getPageNum());
        Pageable pageable = PageRequest.of(pageNumber.getPageNumber() - 1,6, Sort.Direction.ASC,"id" );
        Page<ProductEntity> findPagingProduct = findProductPort.findPagingProduct(pageable);

        return productMapper.mapToDomainEntity(findPagingProduct);
    }

    @Override
    public boolean existProductBySizeId(ExistProductCommand command) {
        return findProductPort.existProductBySize(command.getSizeId());
    }

    @Override
    public List<ProductVo> findProductAll() {

        List<ProductEntity> findProductAll = findProductPort.findProductAll();


        return findProductAll.stream()
                .map(product-> productMapper.mapToDomainEntity(product))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductVo> findProductByProductIds(FindProductByProductIdsCommand command) {

        List<ProductEntity> findProductList = findProductPort.findProductByProductIds(command.getProductIds());

        return findProductList.stream()
                .map(product -> productMapper.mapToDomainEntity(product))
                .collect(Collectors.toList());
    }
}
