package com.example.delivery.module.application.usecase;

import com.example.delivery.module.domain.delivery.service.DeliveryWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryUseCase {

    private final DeliveryWriteService deliveryWriteService;


}
