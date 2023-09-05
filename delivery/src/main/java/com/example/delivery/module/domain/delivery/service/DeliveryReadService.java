package com.example.delivery.module.domain.delivery.service;

import com.example.delivery.module.common.CRUDReadService;
import com.example.delivery.module.domain.delivery.dto.DeliveryDto;
import com.example.delivery.module.domain.delivery.entity.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class DeliveryReadService implements CRUDReadService<Delivery, DeliveryDto> {

    private final ModelMapper modelMapper;
    @Override
    public Delivery read(Long id) {
        return null;
    }

    @Override
    public List<Delivery> readAll() {
        return null;
    }

    @Override
    public Delivery toEntity(DeliveryDto deliveryDto) {
        return modelMapper.map(deliveryDto,Delivery.class);
    }
}
