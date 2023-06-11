package com.example.adminservice.module.domain.size.service;

import com.example.adminservice.module.domain.size.dto.SizeDto;
import com.example.adminservice.module.domain.size.entity.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SizeReadService {

    private final ModelMapper modelMapper;
    public SizeDto toSizeDto(Size size){
        SizeDto sizeDto = modelMapper.map(size, SizeDto.class);
        return sizeDto;
    }
}
