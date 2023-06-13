package com.example.adminservice.module.domain.quantity.service;

import com.example.adminservice.module.domain.quantity.entity.Quantity;
import com.example.adminservice.module.domain.quantity.repository.QuantityRepository;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class QuantityWriteService {

    private final QuantityRepository quantityRepository;

    public Quantity createQuantity(int quantity, SizeQuantity sizeQuantity){
        Quantity createQuantity = Quantity.builder().
                quantity(quantity).
                sizeQuantityList(List.of(sizeQuantity)).
                build();

        return quantityRepository.save(createQuantity);
    }
}
