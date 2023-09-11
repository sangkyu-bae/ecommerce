package com.example.adminservice.application.controller;

import com.example.adminservice.application.usecase.CategoryUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.domain.category.dto.CategoryDto;
import com.example.adminservice.domain.category.validtor.CateGoryDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
            throw new CustomException(ErrorCodet.CATEGORY_FORM_NO_VALIDATE,"createCategory");
        }

        CategoryDto createCategory = categoryUseCase.createCategoryExecute(categoryDto);

        return ResponseEntity.ok().body(createCategory);
    }

    @GetMapping("/admin/categorys")
    public ResponseEntity<List<CategoryDto>> readAllCategory(){
        var categoryList = categoryUseCase.readAllCategoryDtoExecute();
        return ResponseEntity.ok().body(categoryList);
    }

}
