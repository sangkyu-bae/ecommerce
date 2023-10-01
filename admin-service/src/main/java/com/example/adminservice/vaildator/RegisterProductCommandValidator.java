package com.example.adminservice.vaildator;

import com.example.adminservice.adapter.out.persistence.repository.BrandEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.CategoryEntityRepository;
import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.application.port.in.product.RegisterProductComponentCommand;
import com.example.adminservice.application.port.in.product.RegisterSizeCommand;
import com.example.adminservice.domain.productentity.SizeVo;
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
    private final BrandEntityRepository brandRepository;
    private final CategoryEntityRepository entityRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RegisterProductCommand.class);
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        RegisterProductCommand registerProductCommand = (RegisterProductCommand) target;
        registerProductCommand.validateSelf();
        if(!brandRepository.existsById(registerProductCommand.getBrand().getId())){
            errors.rejectValue("brand","Invalid.Brand");
        }
        if(!entityRepository.existsById(registerProductCommand.getCategory().getId())){
            errors.rejectValue("category","Invalid.Category");
        }
         Set<RegisterProductComponentCommand> registerProductComponentCommandSet = registerProductCommand.getProductComponents();

        List<Integer> sizeList = SizeVo.getSize();
        List<Integer> accumulateSizeList = new ArrayList<>();
        for(RegisterProductComponentCommand registerProductComponentCommand : registerProductComponentCommandSet){
            for(RegisterSizeCommand sizeCommand :registerProductComponentCommand.getSizes()){
                int size = sizeCommand.getSize();
                if(!sizeList.contains(size)||accumulateSizeList.contains(size)){
                    errors.rejectValue("size","Invalid.Size");
                }
                accumulateSizeList.add(size);
            }
        }
    }
}
