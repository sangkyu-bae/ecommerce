package com.example.adminservice.adapter.out.persistence.repository;

import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import com.example.adminservice.adapter.out.persistence.product.entity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductEntityRepositoryExtensionImpl extends QuerydslRepositorySupport implements ProductEntityRepositoryExtension  {

    public ProductEntityRepositoryExtensionImpl() {
        super(ProductEntity.class);
    }

    @Override
    public Page<ProductEntity> findWithPageByAll(Pageable pageable) {
        QProductEntity qProductEntity  = QProductEntity.productEntity;
        QColorEntity qColorEntity = QColorEntity.colorEntity;
        QCategoryEntity qCategoryEntity = QCategoryEntity.categoryEntity;
        QSizeEntity qSizeEntity = QSizeEntity.sizeEntity;
        QProductComponentEntity qProductComponentEntity = QProductComponentEntity.productComponentEntity;
        QBrandEntity qBrand = QBrandEntity.brandEntity;

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
}
