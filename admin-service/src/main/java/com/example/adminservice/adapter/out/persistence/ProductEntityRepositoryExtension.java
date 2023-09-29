package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductEntityRepositoryExtension {

    Page<ProductEntity> findWithPageByAll(Pageable pageable);
}
