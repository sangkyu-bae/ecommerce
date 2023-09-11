package com.example.adminservice.domain.size.service;

import com.example.adminservice.domain.size.entity.SizeQuantity;
import com.example.adminservice.domain.size.repository.SizeQuantityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class SizeQuantityWriteService {
    private final SizeQuantityRepository sizeQuantityRepository;

    public SizeQuantity createSizeQuantity(SizeQuantity sizeQuantity){
        return sizeQuantityRepository.save(sizeQuantity);
    }
}
