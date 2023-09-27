package com.example.adminservice.common;

import com.example.adminservice.module.common.error.ErrorCode;
import org.springframework.validation.Errors;

import javax.validation.*;
import java.util.Set;

public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void validateSelf(){
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }

}
