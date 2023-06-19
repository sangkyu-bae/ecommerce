package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.common.method.CommonMethod;
import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.category.entity.Category;
import com.example.adminservice.module.domain.category.service.CategoryReadService;
import com.example.adminservice.module.domain.category.service.CategoryWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryUseCase {
    private final CategoryWriteService categoryWriteService;
    private final CategoryReadService categoryReadService;
    private final CommonMethod commonMethod;
    public CategoryDto createCategoryExecute(CategoryDto categoryDto){
        Category category = categoryWriteService.createCategory(categoryDto);
        CategoryDto toCategory = categoryReadService.toCategoryDto(category);

        return toCategory;
    }

    public List<CategoryDto> readAllCategoryDtoExecute(){
        var categoryList = categoryReadService.readAll();
        var categoryDtoList = categoryList.stream().
                map(category -> commonMethod.toDto(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }
}
