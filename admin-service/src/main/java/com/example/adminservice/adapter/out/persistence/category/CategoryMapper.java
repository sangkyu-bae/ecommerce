package com.example.adminservice.adapter.out.persistence.category;

import com.example.adminservice.adapter.out.persistence.entity.CategoryEntity;
import com.example.adminservice.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapToCategory(CategoryEntity categoryEntity){
        return Category.createGenerateCategory(
                new Category.CategoryId(categoryEntity.getId()),
                new Category.CategoryName(categoryEntity.getName())
        );
    }
}
