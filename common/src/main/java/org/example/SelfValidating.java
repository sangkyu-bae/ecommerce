package org.example;

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
