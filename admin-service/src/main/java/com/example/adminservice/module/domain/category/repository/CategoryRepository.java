package com.example.adminservice.module.domain.category.repository;

import com.example.adminservice.module.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
}
