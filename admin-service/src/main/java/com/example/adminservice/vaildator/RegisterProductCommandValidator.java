package com.example.adminservice.vaildator;

import com.example.adminservice.adapter.out.persistence.CategoryEntityRepository;
import com.example.adminservice.application.port.in.product.RegisterBrandCommand;
import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.application.port.in.product.RegisterProductComponentCommand;
import com.example.adminservice.application.port.in.product.RegisterSizeCommand;
import com.example.adminservice.domain.brand.repository.BrandRepository;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.BrandErrorCode;
import com.example.adminservice.module.common.error.errorImpl.CategoryErrorCode;
import com.example.adminservice.module.common.method.CommonMethod;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegisterProductCommandValidator implements Validator {
    private final BrandRepository brandRepository;
    private final CategoryEntityRepository entityRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RegisterProductCommand.class);
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        RegisterProductCommand registerProductCommand = (RegisterProductCommand) target;
        if(!brandRepository.existsById(registerProductCommand.getBrand().getId())){
            throw new ErrorException(BrandErrorCode.BRAND_NOT_FOUND,"registerProduct");
        }
        if(!entityRepository.existsById(registerProductCommand.getCategory().getId())){
            throw new ErrorException(CategoryErrorCode.BRAND_NOT_FOUND,"registerProduct");
        }

         Set<RegisterProductComponentCommand> registerProductComponentCommandSet = registerProductCommand.getProductComponents();

        List<Integer> sizeList = CommonMethod.SIZE_LIST;
        List<Integer> accumulateSizeList = new ArrayList<>();
        for(RegisterProductComponentCommand registerProductComponentCommand : registerProductComponentCommandSet){
            for(RegisterSizeCommand sizeCommand :registerProductComponentCommand.getSizes()){
                int size = sizeCommand.getSize();
                if(!sizeList.contains(size)||accumulateSizeList.contains(size)){
                    throw new Exception();
                }
                accumulateSizeList.add(size);
            }
        }
    }
}
