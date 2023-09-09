package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
import com.example.adminservice.module.domain.product.OrderDto;
import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import com.example.adminservice.module.domain.quantity.repository.QuantityRepository;
import com.example.adminservice.module.domain.quantity.service.QuantityReadService;
import com.example.adminservice.module.domain.quantity.service.QuantityWriteService;
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
