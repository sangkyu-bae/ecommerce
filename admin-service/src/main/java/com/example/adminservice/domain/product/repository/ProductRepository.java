package com.example.adminservice.domain.product.repository;

import com.example.adminservice.adapter.out.persistence.Product;
import com.example.adminservice.domain.product.repository.querydsl.ProductRepositoryExtension;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product,Long>, ProductRepositoryExtension {

    Product findByName(String name);

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"brand","category","colorProductList"})
    Optional<Product> findWithBrandAndCategoryAndColorProductById(long productId);
}
