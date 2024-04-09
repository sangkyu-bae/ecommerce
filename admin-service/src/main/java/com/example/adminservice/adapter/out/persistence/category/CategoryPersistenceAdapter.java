package com.example.adminservice.adapter.out.persistence.category;

import com.example.adminservice.adapter.out.persistence.entity.CategoryEntity;
import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;
import com.example.adminservice.adapter.out.persistence.repository.CategoryEntityRepository;
import com.example.adminservice.application.port.out.category.FindCategoryPort;
import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;

import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class CategoryPersistenceAdapter implements FindCategoryPort {

    private final CategoryEntityRepository categoryEntityRepository;
    @Override
    public List<CategoryEntity> findCategoryEntityAll() {
        return categoryEntityRepository.findAll();
    }
}
