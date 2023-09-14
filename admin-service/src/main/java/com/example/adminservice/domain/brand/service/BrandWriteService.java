package com.example.adminservice.domain.brand.service;

import com.example.adminservice.domain.brand.repository.BrandRepository;
import com.example.adminservice.module.common.CRUDWriteService;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.domain.brand.dto.BrandDto;
import com.example.adminservice.domain.brand.entity.Brand;
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
public class BrandWriteService implements CRUDWriteService<Brand,BrandDto> {

    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public Brand createBrand(BrandDto brandDto){
        Brand brand = modelMapper.map(brandDto,Brand.class);
        brand.setCreateAt(LocalDate.now());
        brand.setUpdateAt(LocalDate.now());

        return brandRepository.save(brand);
    }
    @Override
    public void delete(long brandId) {
        if(!brandRepository.existsById(brandId)){
            throw new CustomException(ErrorCodet.BRAND_NOT_FOUND,"removeBrand");
        }
        brandRepository.deleteById(brandId);
    }

    @Override
    public Brand update(Brand brand, BrandDto updateBrandDto) {
        brand.setUpdateAt(LocalDate.now());
        modelMapper.map(updateBrandDto, brand);
        return brand;
    }
}