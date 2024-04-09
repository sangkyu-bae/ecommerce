package com.example.adminservice.adapter.out.persistence.brand;

import com.example.adminservice.adapter.out.persistence.entity.BrandEntity;
import com.example.adminservice.adapter.out.persistence.repository.BrandEntityRepository;
import com.example.adminservice.application.port.out.product.FindBrandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@PersistenceAdapter
@Slf4j
public class BrandPersistenceAdapter implements FindBrandPort {

    private final BrandEntityRepository brandEntityRepository;
    @Override
    public List<BrandEntity> findBrandAll() {
        return brandEntityRepository.findAll();
    }
}
