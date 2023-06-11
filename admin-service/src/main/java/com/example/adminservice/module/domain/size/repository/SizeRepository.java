package com.example.adminservice.module.domain.size.repository;

import com.example.adminservice.module.domain.size.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SizeRepository extends JpaRepository<Size,Long> {
}
