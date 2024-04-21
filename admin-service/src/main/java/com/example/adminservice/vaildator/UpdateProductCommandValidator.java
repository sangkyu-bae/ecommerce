package com.example.adminservice.vaildator;

import com.example.adminservice.adapter.out.persistence.repository.BrandEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.CategoryEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.ColorEntityRepository;
import com.example.adminservice.adapter.out.persistence.repository.SizeEntityRepository;
import com.example.adminservice.application.port.in.command.RegisterProductComponentCommand;
import com.example.adminservice.application.port.in.command.RegisterSizeCommand;
import com.example.adminservice.application.port.in.command.UpdateProductCommand;
import com.example.adminservice.domain.SizeCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UpdateProductCommandValidator implements Validator {

    private final BrandEntityRepository brandEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final ColorEntityRepository colorEntityRepository;
    private final SizeEntityRepository sizeEntityRepository;

    private List<Integer> SIZE_LIST;
    private List<Integer> ACCUMULATE_SIZE_LIST = new ArrayList<>();

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UpdateProductCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateProductCommand updateProductCommand = (UpdateProductCommand) target;
        updateProductCommand.validateSelf();

        if(!existByBrand(updateProductCommand)){
            errors.rejectValue("brand","Invalid.Brand");
        }
        if(!existByCategory(updateProductCommand)){
            errors.rejectValue("category","Invalid.Category");
        }
        Set<RegisterProductComponentCommand> updateProductComponentCommandSet = updateProductCommand.getProductComponents();

        SIZE_LIST = SizeCheck.getSize();

        for(RegisterProductComponentCommand updateProductComponentCommand : updateProductComponentCommandSet){
            ACCUMULATE_SIZE_LIST.clear();
            if(!existByColor(updateProductComponentCommand)){
                errors.rejectValue("color","Invalid.Color");
            }
            for(RegisterSizeCommand sizeCommand :updateProductComponentCommand.getSizes()){
                int size =sizeCommand.getSize();
                if(!checkSizeValid(sizeCommand)){
                    errors.rejectValue("size","Invalid.Size");
                }
                ACCUMULATE_SIZE_LIST.add(size);
            }
        }
    }


    private boolean checkSizeValid(RegisterSizeCommand sizeCommand){
        int size = sizeCommand.getSize();
        if(!SIZE_LIST.contains(size)||ACCUMULATE_SIZE_LIST.contains(size)){
            return false;
        }
        if(sizeCommand.getId() > 0 && !sizeEntityRepository.existsById(sizeCommand.getId())){
            return false;
        }

        return true;
    }

    private boolean existByColor(RegisterProductComponentCommand updateProductComponentCommand) {
        return colorEntityRepository.existsById(updateProductComponentCommand.getColor().getId());
    }

    private boolean existByCategory(UpdateProductCommand updateProductCommand) {
        return categoryEntityRepository.existsById(updateProductCommand.getCategory().getId());
    }

    private boolean existByBrand(UpdateProductCommand updateProductCommand) {
        return brandEntityRepository.existsById(updateProductCommand.getBrand().getId());
    }
}
