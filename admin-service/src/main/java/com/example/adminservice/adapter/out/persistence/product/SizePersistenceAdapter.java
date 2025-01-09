package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.out.persistence.entity.SizeEntity;
import com.example.adminservice.adapter.out.persistence.repository.SizeEntityRepository;
import com.example.adminservice.application.port.out.size.FindSizePort;
import com.example.adminservice.application.port.out.size.UpdateSizePort;
import com.example.adminservice.domain.SizeVo;
import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SizePersistenceAdapter implements FindSizePort, UpdateSizePort {

    private final SizeEntityRepository sizeEntityRepository;

    public SizeEntity findById(SizeVo.SizeId sizeId){
        return sizeEntityRepository.findById(sizeId.getId()).orElseThrow(()-> new ErrorException(ProductErrorCode.PRODUCT_NOT_FOUND,"findById"));
    }

    @Override
    public int updateQuantity(SizeVo.SizeId sizeId,int amount) {

        SizeEntity sizeEntity = sizeEntityRepository.findById(sizeId.getId()).orElseThrow(()-> new ErrorException(ProductErrorCode.PRODUCT_NOT_FOUND,"updateQuantity"));
        sizeEntity.updateQuantity(amount);

        return sizeEntity.getQuantity();

    }
}
