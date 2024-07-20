package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.out.persistence.entity.SizeEntity;
import com.example.adminservice.adapter.out.persistence.repository.SizeEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.SpringDataProductRepository;
import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;

import com.example.adminservice.application.port.out.brand.*;
import com.example.adminservice.domain.ProductVo;
import com.example.adminservice.domain.SizeVo;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductPersistenceAdapter implements FindProductPort,
        UpdateProductPort,
        RegisterProductPort,
        RemoveProductPort,
        UpdateProductSizePort {
    private final SpringDataProductRepository springDataProductRepository;

    private final ProductMapper productMapper;

    private final SizeEntityRepository sizeEntityRepository;

    @Override
    public ProductEntity createProduct(ProductVo productVo) {

        ProductEntity createProductEntity = ProductEntity.builder()
                .name(productVo.getName())
                .price(productVo.getPrice())
                .description(productVo.getDescription())
                .productImage(productVo.getProductImage())
                .brand(productMapper.mapToBrand(productVo.getBrand()))
                .category(productMapper.mapToCategory(productVo.getCategory()))
                .productComponents(productMapper.mapToProductComponents(productVo.getProductComponents()))
                .aggregateIdentifier(productVo.getAggregateIdentifier())
                .build();

        createProductEntity.getProductComponents()
                .forEach(component -> {
                            component.setProduct(createProductEntity);
                            component.getSizes().forEach(size -> size.setProductComponent(component));
                        }
                );

        return springDataProductRepository.save(createProductEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity findProduct(ProductVo.ProductId productId) {
        return springDataProductRepository.findById(productId.getId())
                .orElseThrow(() -> new ErrorException(ProductErrorCode.PRODUCT_FORM_NO_VALIDATE, "findProduct"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductEntity> findPagingProduct(Pageable pageable) {

        long startTime = System.currentTimeMillis();
//        Page<ProductEntity> productPage = springDataProductRepository.findWithPageByAll(pageable);
        Page<ProductEntity> productPage = springDataProductRepository.findTest(pageable);
//        Page<ProductEntity> productPage = springDataProductRepository.findAll(pageable);
        System.out.println(productPage.getTotalElements());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Execution time for findPagingProduct Query : {} ms", duration);
        return productPage;
    }

    @Override
    public Page<ProductEntity> findPagingProductByCategory(Pageable pageable, long categoryId) {
        Page<ProductEntity> productPage = springDataProductRepository.findWithPageByCategoryId(pageable,categoryId);
        return productPage;
    }

    @Override
    public boolean existProductBySize(long sizeId) {
        return sizeEntityRepository.existsById(sizeId);
    }

    @Override
    public List<ProductEntity> findProductAll() {
        return springDataProductRepository.findAll();
    }

    @Override
    public List<ProductEntity> findProductByProductIds(List<Long> productIds) {
        return springDataProductRepository.findByIdIn(productIds);
    }

    @Override
    public ProductEntity updateProduct(ProductVo productVo) {
        ProductEntity updateProductEntity = ProductEntity.builder()
                .id(productVo.getId())
                .name(productVo.getName())
                .price(productVo.getPrice())
                .description(productVo.getDescription())
                .productImage(productVo.getProductImage())
                .brand(productMapper.mapToBrand(productVo.getBrand()))
                .category(productMapper.mapToCategory(productVo.getCategory()))
                .productComponents(productMapper.mapToProductComponents(productVo.getProductComponents()))
                .build();

        updateProductEntity.getProductComponents()
                .forEach(component -> {
                            component.setProduct(updateProductEntity);
                            component.getSizes().forEach(size -> size.setProductComponent(component));
                        }
                );

        return springDataProductRepository.save(updateProductEntity);
    }

    @Override
    public boolean removeProduct(ProductVo.ProductId productId) {
        try {
            springDataProductRepository.deleteById(productId.getId());
        } catch (Exception e) {
            throw new ErrorException(ProductErrorCode.PRODUCT_NOT_FOUND, "removeProduct");
        }

        return true;
    }

    @Override
    public void updateProductSize(SizeVo sizeVo) {
        SizeEntity sizeEntity = sizeEntityRepository.findById(sizeVo.getId())
                .orElseThrow(()->new ErrorException(ProductErrorCode.PRODUCT_NOT_FOUND,"updateProductSize"));

        sizeEntity.updateQuantity(sizeVo.getQuantity());
    }
}
