package com.example.adminservice.adapter.out.persistence.repository;

import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductEntityRepositoryExtension {

    Page<ProductEntity> findWithPageByAll(Pageable pageable);

    Page<ProductEntity> findTest(Pageable pageable);

    Page<ProductEntity> findWithPageByCategoryId(Pageable pageable, long categoryId);

}
