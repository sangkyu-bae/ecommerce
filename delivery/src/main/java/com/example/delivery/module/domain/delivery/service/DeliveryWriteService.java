package com.example.delivery.module.domain.delivery.service;

import com.example.delivery.module.common.CRUDWriteService;
import com.example.delivery.module.domain.delivery.dto.DeliveryDto;
import com.example.delivery.module.domain.delivery.entity.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeliveryWriteService implements CRUDWriteService<Delivery, DeliveryDto> {
    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public Delivery update(Delivery domain) {
        return null;
    }

    @Override
    public Delivery create(DeliveryDto domain) {
        return null;
    }
}
