package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
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

    private final QuantityRepository quantityRepository;

    public int updateQuantity(Long quantityId, int buyQuantity){
        Quantity quantity = quantityReadService.read(quantityId);
        quantityWriteService.test(quantity,buyQuantity);
//        Quantity quantity = read(quantityId);
//        test(quantity,buyQuantity);
        System.out.println(quantity.getQuantity());
        return quantity.getQuantity();
    }
    @Transactional
    public Quantity test(Quantity quantity,int qua){
        quantity.changeQuantity(qua);
        return quantity;
    }
    @Transactional
    public Quantity read(long quantityId) {
        Quantity quantity = quantityRepository.findById(quantityId).
                orElseThrow(()->new ErrorException(QuantityErrorCode.QUANTITY_NOT_FOUND,"read"));
        return quantity;
    }
}
