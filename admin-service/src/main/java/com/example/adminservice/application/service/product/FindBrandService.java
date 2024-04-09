package com.example.adminservice.application.service.product;

import com.example.adminservice.adapter.out.persistence.entity.BrandEntity;
import com.example.adminservice.adapter.out.persistence.brand.BrandMapper;
import com.example.adminservice.application.port.in.usecase.brand.FindBrandUseCase;
import com.example.adminservice.application.port.out.product.FindBrandPort;
import com.example.adminservice.domain.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class FindBrandService implements FindBrandUseCase {

    private final FindBrandPort findBrandPort;

    private final BrandMapper brandMapper;
    @Override
    public List<Brand> findBrandAll() {
        List<BrandEntity> brandEntityList = findBrandPort.findBrandAll();
        return brandEntityList.stream()
                .map(brand -> brandMapper.mapToBrand(brand))
                .collect(Collectors.toList());
    }
}
