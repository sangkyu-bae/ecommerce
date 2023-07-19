package com.example.adminservice.module.domain.product.validator;

import com.example.adminservice.module.domain.brand.repository.BrandRepository;
import com.example.adminservice.module.domain.category.repository.CategoryRepository;
import com.example.adminservice.module.domain.color.repository.ColorRepository;
import com.example.adminservice.module.domain.product.dto.CreateProductDto;
import com.example.adminservice.module.domain.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateProductDtoValidator implements Validator {

    private final ColorRepository colorRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateProductDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateProductDto createProductDto =(CreateProductDto) target;

        if(!brandRepository.existsById(createProductDto.getBrand().getId())){
            errors.rejectValue("brand","Invalid.Brand");
        }
        if(!categoryRepository.existsById(createProductDto.getCategory().getId())){
            errors.rejectValue("category","Invalid.Category");
        }

        createProductDto.getColorDataList().stream()
                .filter(colorDataDto -> !colorRepository.existsById(colorDataDto.getColorName().getId()))
                .findAny()
                .ifPresent(colorDataDto -> errors.rejectValue("color", "Invalid.Color"));
    }
}
