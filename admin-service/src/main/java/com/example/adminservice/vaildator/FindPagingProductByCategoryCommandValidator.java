package com.example.adminservice.vaildator;

import com.example.adminservice.adapter.out.persistence.repository.CategoryEntityRepository;
import com.example.adminservice.application.port.in.command.FindPagingProductByCategoryCommand;
import com.example.adminservice.infra.common.CategoryCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindPagingProductByCategoryCommandValidator implements Validator {

    private final CategoryEntityRepository categoryEntityRepository;

    private final CategoryCache categoryCache;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FindPagingProductByCategoryCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FindPagingProductByCategoryCommand command = (FindPagingProductByCategoryCommand) target;

        try{
            if(!existByCategory(command.getCategoryId())){
                errors.rejectValue("category","Invalid.Category");
            }
        }catch (Exception e){
            log.error("dbcheck");
            e.printStackTrace();
        }

        try{
            if(!categoryCache.existsByCategoryId(command.getCategoryId())){
                errors.rejectValue("category","Invalid.Category");
            }
        }catch (Exception e){
            log.error("cachecheck");
            errors.rejectValue("category","Invalid.Category");
            e.printStackTrace();
        }

    }

    private boolean existByCategory(long categoryId) {
        return categoryEntityRepository.existsById(categoryId);
    }
}

