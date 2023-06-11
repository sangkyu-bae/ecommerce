package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.CategoryUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.category.repository.CategoryRepository;
import com.example.adminservice.module.domain.category.validtor.CateGoryDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CategoryController {
    private final CateGoryDtoValidator cateGoryDtoValidator;
    private final CategoryUseCase categoryUseCase;

    @InitBinder("categoryDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(cateGoryDtoValidator);
    }

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto, Errors errors){
        if(errors.hasErrors()){
            throw new CustomException(ErrorCode.CATEGORY_FORM_NO_VALIDATE,"createCategory");
        }

        CategoryDto createCategory = categoryUseCase.createCategoryExecute(categoryDto);

        return ResponseEntity.ok().body(createCategory);
    }

}
