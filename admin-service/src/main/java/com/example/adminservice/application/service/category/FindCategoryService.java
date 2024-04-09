package com.example.adminservice.application.service.category;

import com.example.adminservice.adapter.out.persistence.category.CategoryMapper;
import com.example.adminservice.adapter.out.persistence.entity.CategoryEntity;
import com.example.adminservice.application.port.in.usecase.category.FindCategoryUseCase;
import com.example.adminservice.application.port.out.category.FindCategoryPort;
import com.example.adminservice.domain.Category;
import lombok.RequiredArgsConstructor;
import org.example.UseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class FindCategoryService implements FindCategoryUseCase {

    private final FindCategoryPort findCategoryPort;

    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryAll() {

        List<CategoryEntity> categoryEntityList = findCategoryPort.findCategoryEntityAll();
        return categoryEntityList.stream()
                .map(categoryEntity -> categoryMapper.mapToCategory(categoryEntity))
                .collect(Collectors.toList());
    }
}
