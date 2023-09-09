package com.example.adminservice.module.domain.quantity.service;

import com.example.adminservice.module.common.CRUDWriteService;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
import com.example.adminservice.module.common.method.CommonMethod;
import com.example.adminservice.module.domain.product.OrderDto;
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
public class QuantityWriteService implements CRUDWriteService<Quantity,Quantity> {

    private final QuantityRepository quantityRepository;
    public Quantity createQuantity(int quantity, SizeQuantity sizeQuantity){
        Quantity createQuantity = Quantity.builder().
                quantity(quantity).
                sizeQuantityList(List.of(sizeQuantity)).
                build();

        return quantityRepository.save(createQuantity);
    }

    public Quantity creatQuantity(int quantity){
        Quantity createQuantity = Quantity.builder().
                quantity(quantity).
                build();

        return quantityRepository.save(createQuantity);
    }

    @Override
    public void delete(long id) {
        if(!quantityRepository.existsById(id)){
            throw new ErrorException(QuantityErrorCode.QUANTITY_NOT_FOUND,"delete");
        }
        quantityRepository.deleteById(id);
    }

    @Override
    public Quantity update(Quantity quantity, Quantity updateQuantity) {
        if(updateQuantity.getQuantity() != quantity.getQuantity()){
            quantity.changeQuantity(updateQuantity.getQuantity());
        }
        return quantity;
    }

    public Quantity buyProductQuantity(OrderDto orderDto,Quantity quantity){
        quantity.changeQuantity(orderDto.getAmount());
        return quantity;
    }

}
