package com.example.adminservice.adapter.out.persistence.repository;

import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ColorEntityRepository extends JpaRepository<ColorEntity,Long> {
}
