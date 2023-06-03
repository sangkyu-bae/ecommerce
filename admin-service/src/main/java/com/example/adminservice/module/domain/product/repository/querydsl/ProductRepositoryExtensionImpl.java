package com.example.adminservice.module.domain.product.repository.querydsl;

import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.entity.QProduct;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductRepositoryExtensionImpl extends QuerydslRepositorySupport implements ProductRepositoryExtension {

    public ProductRepositoryExtensionImpl(){
        super(Product.class);
    }


    /**
     * 새로운 테이블 생성시 사용하기 위함 현재는 전체를 읽도록 설정
     *
     * */
    @Override
    public Page<Product> findWithPageByAll(Pageable pageable) {
        QProduct Product = QProduct.product;

        JPQLQuery<Product> query = from(Product);
        JPQLQuery<Product> pageableQuery =getQuerydsl().applyPagination(pageable, query);

        QueryResults<Product> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(),pageable,fetchResults.getTotal());
    }
}
