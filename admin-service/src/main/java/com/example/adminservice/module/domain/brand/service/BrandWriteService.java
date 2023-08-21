package com.example.adminservice.module.domain.brand.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.brand.entity.Brand;
import com.example.adminservice.module.domain.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BrandWriteService {

    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public Brand createBrand(BrandDto brandDto){
        Brand brand = modelMapper.map(brandDto,Brand.class);
        brand.setCreateAt(LocalDate.now());
        brand.setUpdateAt(LocalDate.now());

        return brandRepository.save(brand);
    }

    public void removeBrand(long brandId){
        if(!brandRepository.existsById(brandId)){
            throw new CustomException(ErrorCodet.BRAND_NOT_FOUND,"removeBrand");
        }

        brandRepository.deleteById(brandId);
    }

    public Brand updateBrand(Brand brand, BrandDto updateBrandDto){
        brand.setUpdateAt(LocalDate.now());
        modelMapper.map(updateBrandDto, brand);
        return brand;
    }



}
