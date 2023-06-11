package com.example.adminservice.module.domain.brand.validator;

import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BrandDtoValidator implements Validator {

    private final BrandRepository brandRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(BrandDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BrandDto brandDto = (BrandDto) target;

        if(brandRepository.existsByName(brandDto.getName())){
            errors.rejectValue("brandName","이미 존재하는 브랜드명 입니다.");
        }
    }
}
