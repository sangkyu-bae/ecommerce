package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.BrandUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.brand.service.BrandWriteService;
import com.example.adminservice.module.domain.brand.validator.BrandDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody BrandDto brandDto,
                                                @RequestHeader("X-User-Id") String userId , Errors errors){
        if(errors.hasErrors()){
            throw new CustomException(ErrorCode.BRAND_NOT_FOUND,"createBrand");
        }

        BrandDto createBrandDto = brandUseCase.createBrandExecute(brandDto);
        return ResponseEntity.ok().body(createBrandDto);
    }

}
