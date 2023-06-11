package com.example.adminservice.module.domain.color.repository;

import com.example.adminservice.module.domain.color.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ColorRepository extends JpaRepository<Color,Long> {
}
