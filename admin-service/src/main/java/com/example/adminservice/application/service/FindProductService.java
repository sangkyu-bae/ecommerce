package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.FindProductUseCase;
import com.example.adminservice.application.port.in.product.FindPagingProductCommand;
import com.example.adminservice.application.port.in.product.FindProductCommand;
import com.example.adminservice.application.port.out.FindProductPort;

import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class FindProductService implements FindProductUseCase {
    private final FindProductPort findProductPort;
    private final ProductMapper productMapper;

    private final StringRedisTemplate redisTemplate;

    private static final String PRODUCT_KEY = "leaderProduct";

    @Override
    public ProductVo findProduct(FindProductCommand command) {
        ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
        ProductEntity findProduct =  findProductPort.findProduct(productId);

        String productName = findProduct.getName();
        int clickCnt = getProductClickCount(productName);
        setClickNum(productName,clickCnt);

        return productMapper.mapToDomainEntity(findProduct);
    }

    private void setClickNum(String productName,int clickCnt){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(PRODUCT_KEY,productName,clickCnt+1);
    }

    private int getProductClickCount(String productName){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(PRODUCT_KEY, productName);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }


    @Override
    public ProductSearchVo findPagingProduct(FindPagingProductCommand command) {
        ProductSearchVo.PageNumber pageNumber = new ProductSearchVo.PageNumber(command.getPageNum());
        Pageable pageable = PageRequest.of(pageNumber.getPageNumber() - 1,6, Sort.Direction.ASC,"id" );
        Page<ProductEntity> findPagingProduct = findProductPort.findPagingProduct(pageable);

        return productMapper.mapToDomainEntity(findPagingProduct);
    }
}
