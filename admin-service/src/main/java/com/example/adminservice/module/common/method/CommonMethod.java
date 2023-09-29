package com.example.adminservice.module.common.method;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonMethod {
    private final ModelMapper modelMapper;
    public <T, D> D toDto(T entity, Class<D> dtoClass) {
        try {
            return modelMapper.map(entity, dtoClass);
        } catch (CustomException exception) {
            throw new CustomException(ErrorCodet.UNPROCESSABLE_ENTITY, "toDto");
        }
    }
}
