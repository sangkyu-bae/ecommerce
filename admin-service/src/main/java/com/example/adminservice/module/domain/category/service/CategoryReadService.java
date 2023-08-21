package com.example.adminservice.module.domain.category.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.category.entity.Category;
import com.example.adminservice.module.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class CategoryReadService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = null;
        try{
            categoryDto = modelMapper.map(category, CategoryDto.class);
        }catch (CustomException exception){
            throw new CustomException(ErrorCodet.CATEGORY_UNPROCESSABLE_ENTITY,"toCategoryDto");
        }

        return categoryDto;
    }

    public Category readCategory(String categoryName){
        Category category = categoryRepository.findByName(categoryName).orElseThrow(()->new CustomException(ErrorCodet.CATEGORY_NOT_FOUND,"readCreate"));
        return category;
    }

    public List<Category> readAll(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
