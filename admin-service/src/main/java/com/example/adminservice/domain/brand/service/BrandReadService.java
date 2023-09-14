package com.example.adminservice.domain.brand.service;

import com.example.adminservice.domain.brand.repository.BrandRepository;
import com.example.adminservice.module.common.CRUDReadService;
import com.example.adminservice.module.common.error.errorImpl.BrandErrorCode;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.domain.brand.dto.BrandDto;
import com.example.adminservice.domain.brand.entity.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BrandReadService implements CRUDReadService<Brand> {

    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    @Override
    public Brand read(long id) {
        return brandRepository.findById(id)
                .orElseThrow(()->new ErrorException(BrandErrorCode.BRAND_NOT_FOUND,"read"));
    }

    @Override
    public List<Brand> readAll(){
        List<Brand> brandList = brandRepository.findAll();
        return brandList;
    }

    public BrandDto toBrandDto(Brand brand){
        BrandDto brandDto =null;
        try{
            brandDto = modelMapper.map(brand,BrandDto.class);
        }catch (ErrorException exception){
            throw new ErrorException(BrandErrorCode.BRAND_UNPROCESSABLE_ENTITY,"toBrandDto");
        }

        return brandDto;
    }


}