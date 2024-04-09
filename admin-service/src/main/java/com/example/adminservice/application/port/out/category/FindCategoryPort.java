package com.example.adminservice.application.port.out.category;

import com.example.adminservice.adapter.out.persistence.entity.CategoryEntity;
import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;

import java.util.List;

public interface FindCategoryPort {
    List<CategoryEntity> findCategoryEntityAll();
}
