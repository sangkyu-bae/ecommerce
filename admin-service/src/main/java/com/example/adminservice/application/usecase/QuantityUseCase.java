package com.example.adminservice.application.usecase;

import com.example.adminservice.domain.quantity.entity.Quantity;
import com.example.adminservice.domain.quantity.service.QuantityReadService;
import com.example.adminservice.domain.quantity.service.QuantityWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QuantityUseCase {

    private final QuantityWriteService quantityWriteService;

    private final QuantityReadService quantityReadService;

    public int updateQuantity(Long quantityId, int buyQuantity){
        Quantity quantity = quantityReadService.read(quantityId);
        return quantity.getQuantity();
    }

}
