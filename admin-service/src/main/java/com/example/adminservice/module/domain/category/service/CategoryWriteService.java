package com.example.adminservice.module.domain.category.service;

import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.category.entity.Category;
import com.example.adminservice.module.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CategoryWriteService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public Category createCategory(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto,Category.class);
        return categoryRepository.save(category);
    }
}
