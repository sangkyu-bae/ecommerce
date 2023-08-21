package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.BrandUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.brand.validator.BrandDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BrandController {
    private final BrandDtoValidator addValidators;
    private final BrandUseCase brandUseCase;

    @InitBinder("brandDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(addValidators);
    }

    @PostMapping("/admin/brand")
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody BrandDto brandDto, Errors errors){
        if(errors.hasErrors()){
            throw new CustomException(ErrorCodet.BRAND_FORM_NO_VALIDATE,"createBrand");
        }

        BrandDto createBrandDto = brandUseCase.createBrandExecute(brandDto);
        return ResponseEntity.ok().body(createBrandDto);
    }

    @GetMapping("/admin/brands")
    public ResponseEntity<List<BrandDto>> readBrandAll(){
        var brandList =  brandUseCase.readAllBrandDtoExecute();
        return ResponseEntity.ok().body(brandList);
    }

}
