package com.example.adminservice.adapter.out.persistence.brand;

import com.example.adminservice.adapter.out.persistence.entity.BrandEntity;
import com.example.adminservice.adapter.out.persistence.repository.BrandEntityRepository;
import com.example.adminservice.domain.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    private final BrandEntityRepository brandEntityRepository;

    public BrandMapper(BrandEntityRepository brandEntityRepository) {
        this.brandEntityRepository = brandEntityRepository;
    }

    public Brand mapToBrand(BrandEntity brandEntity){
        return Brand.createGenerateBrand(
                new Brand.BrandId(brandEntity.getId()),
                new Brand.BrandName(brandEntity.getName()),
                new Brand.BrandCreateAt(brandEntity.getCreateAt()),
                new Brand.BrandUpdateAt(brandEntity.getUpdateAt()),
                new Brand.BrandImage(brandEntity.getBrandImage())
        );
    }
}
