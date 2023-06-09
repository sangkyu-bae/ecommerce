package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.common.method.CommonMethod;
import com.example.adminservice.module.domain.brand.dto.BrandDto;
import com.example.adminservice.module.domain.brand.entity.Brand;
import com.example.adminservice.module.domain.brand.service.BrandReadService;
import com.example.adminservice.module.domain.brand.service.BrandWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandUseCase {

    private final BrandWriteService brandWriteService;
    private final BrandReadService brandReadService;

    private final CommonMethod commonMethod;
    public BrandDto createBrandExecute(BrandDto brandDto){
        Brand brand = brandWriteService.createBrand(brandDto);
        BrandDto toBrandDto = brandReadService.toBrandDto(brand);

        return toBrandDto;
    }

    public List<BrandDto> readAllBrandDtoExecute(){
        var brandList = brandReadService.readAllBrand();
        var brandDtpList = brandList.stream().
                map(brand -> commonMethod.toDto(brand,BrandDto.class)).collect(Collectors.toList());

        return brandDtpList;
    }

}
