package com.example.adminservice.domain.category.validtor;

import com.example.adminservice.domain.category.dto.CategoryDto;
import com.example.adminservice.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CateGoryDtoValidator implements Validator {

    private final CategoryRepository categoryRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CategoryDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDto categoryDto = (CategoryDto) target;

        if(categoryRepository.existsByName(categoryDto.getName())){
            errors.rejectValue("categoryName","이미 존재하는 카테고리 입니다.");
        }
    }
}
