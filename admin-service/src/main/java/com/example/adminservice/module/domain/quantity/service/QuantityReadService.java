package com.example.adminservice.module.domain.quantity.service;

import com.example.adminservice.module.common.CRUDReadService;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import com.example.adminservice.module.domain.quantity.repository.QuantityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional(readOnly = true)
//@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class QuantityReadService implements CRUDReadService<Quantity> {

    private final QuantityRepository quantityRepository;

    @Override
    @Transactional
    public Quantity read(long quantityId) {
//        Quantity quantity = quantityRepository.findWithIdForUpdate(quantityId).
//                orElseThrow(()->new ErrorException(QuantityErrorCode.QUANTITY_NOT_FOUND,"read"));
        Quantity quantity = quantityRepository.findById(quantityId).
                orElseThrow(()->new ErrorException(QuantityErrorCode.QUANTITY_NOT_FOUND,"read"));
        return quantity;
    }

    @Override
    public List<Quantity> readAll() {
        return null;
    }
}
