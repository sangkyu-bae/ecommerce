package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.adapter.out.persistence.product.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BrandEntityRepository extends JpaRepository<BrandEntity,Long> {
}
