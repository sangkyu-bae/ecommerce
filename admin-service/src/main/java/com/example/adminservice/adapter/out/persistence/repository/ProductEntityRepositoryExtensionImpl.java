package com.example.adminservice.adapter.out.persistence.repository;

import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;

import com.example.adminservice.adapter.out.persistence.entity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Collections;
import java.util.List;

public class ProductEntityRepositoryExtensionImpl extends QuerydslRepositorySupport implements ProductEntityRepositoryExtension  {

    public ProductEntityRepositoryExtensionImpl() {
        super(ProductEntity.class);
    }

    QProductEntity qProductEntity  = QProductEntity.productEntity;
    QColorEntity qColorEntity = QColorEntity.colorEntity;
    QCategoryEntity qCategoryEntity = QCategoryEntity.categoryEntity;
    QSizeEntity qSizeEntity = QSizeEntity.sizeEntity;
    QProductComponentEntity qProductComponentEntity = QProductComponentEntity.productComponentEntity;
    QBrandEntity qBrand = QBrandEntity.brandEntity;

    @Override
    public Page<ProductEntity> findWithPageByAll(Pageable pageable) {

        JPQLQuery<ProductEntity> query = from(qProductEntity)
                .leftJoin(qProductEntity.brand, qBrand).fetchJoin()
                .leftJoin(qProductEntity.category, qCategoryEntity).fetchJoin()
                .leftJoin(qProductEntity.productComponents, qProductComponentEntity).fetchJoin()
                .leftJoin(qProductComponentEntity.color,qColorEntity).fetchJoin()
                .leftJoin(qProductComponentEntity.sizes,qSizeEntity).fetchJoin()
                .distinct();

        JPQLQuery<ProductEntity> pageableQuery = getQuerydsl().applyPagination(pageable,query);
        QueryResults<ProductEntity> fetchResults = pageableQuery.fetchResults();

        return new PageImpl<>(fetchResults.getResults(),pageable, fetchResults.getTotal());
    }

    @Override
    public Page<ProductEntity> findTest(Pageable pageable) {
        List<Long> productIds = getProductIds(pageable,null);
        List<Long> productComponentIds = getProductComponentIds(productIds);
        List<Long> sizeIds = getSizeIds(productComponentIds);


        if (productIds.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }


        // Step 2: Fetch the full product details for the fetched product IDs
        List<ProductEntity> products = getProduct(productComponentIds,sizeIds,productIds);

        // Total count of products (without pagination)
        long total = getTotalCnt(null);

        return new PageImpl<>(products, pageable, total);
    }

    @Override
    public Page<ProductEntity> findWithPageByCategoryId(Pageable pageable, long categoryId) {
        List<Long> productIds = getProductIds(pageable,categoryId);
        List<Long> productComponentIds = getProductComponentIds(productIds);
        List<Long> sizeIds = getSizeIds(productComponentIds);

        if (productIds.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        // Step 2: Fetch the full product details for the fetched product IDs
        List<ProductEntity> products = getProduct(productComponentIds,sizeIds,productIds);

        // Total count of products (without pagination)
        long total = getTotalCnt(categoryId);

        return new PageImpl<>(products, pageable, total);
    }
    private BooleanExpression categoryEq(Long categoryId) {
        if (categoryId == null) {
            return null;
        }

        return qProductEntity.category.id.eq(categoryId);
    }

    private List<Long> getProductIds(Pageable pageable, Long categoryId){
        List<Long> productIds = getQuerydsl()
                .applyPagination(pageable, from(qProductEntity)
                        .select(qProductEntity.id)
                        .where(categoryEq(categoryId))
                )
                .fetch();

        return productIds;
    }

    private List<Long> getProductComponentIds(List<Long> productIds){
        List<Long> productComponentIds = from(qProductComponentEntity)
                .select(qProductComponentEntity.id)
                .where(qProductComponentEntity.product.id.in(productIds))
                .fetch();

        return productComponentIds;
    }

    private List<Long> getSizeIds(List<Long> productComponentIds){
        List<Long> sizeIds = from(qSizeEntity)
                .select(qSizeEntity.id)
                .where(qSizeEntity.productComponent.id.in(productComponentIds))
                .fetch();

        return sizeIds;
    }

    private List<ProductEntity> getProduct(List<Long> productComponentIds, List<Long> sizeIds, List<Long> productIds){
        List<ProductEntity> products = from(qProductEntity)
                .leftJoin(qProductEntity.brand, qBrand).fetchJoin()
                .leftJoin(qProductEntity.category, qCategoryEntity).fetchJoin()
                .leftJoin(qProductEntity.productComponents, qProductComponentEntity).fetchJoin()
                .leftJoin(qProductComponentEntity.color, qColorEntity).fetchJoin()
                .leftJoin(qProductComponentEntity.sizes, qSizeEntity).fetchJoin()
                .where(
                        qProductEntity.id.in(productIds)
                                .and(qProductComponentEntity.id.in(productComponentIds))
                                .and(qSizeEntity.id.in(sizeIds))
                )
                .distinct()
                .fetch();
        return products;
    }

    private long getTotalCnt(Long categoryId) {
        return from(qProductEntity)
                .where(categoryEq(categoryId))
                .select(qProductEntity.count())
                .fetchOne();
    }
}
