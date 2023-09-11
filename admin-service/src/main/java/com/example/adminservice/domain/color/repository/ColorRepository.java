package com.example.adminservice.domain.color.repository;

import com.example.adminservice.domain.color.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ColorRepository extends JpaRepository<Color,Long> {
    Optional<Color> findByName(String colorName);
}
