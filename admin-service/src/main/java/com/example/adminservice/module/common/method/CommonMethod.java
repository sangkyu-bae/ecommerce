package com.example.adminservice.module.common.method;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommonMethod {
    private final ModelMapper modelMapper;

    public <T, D> D toDto(T entity, Class<D> dtoClass) {
        try {
            return modelMapper.map(entity, dtoClass);
        } catch (CustomException exception) {
            throw new CustomException(ErrorCode.UNPROCESSABLE_ENTITY, "toDto");
        }
    }
}
