package com.example.adminservice.module.domain.color.service;

import com.example.adminservice.module.domain.color.dto.ColorDto;
import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.color.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ColorReadService {
    private final ColorRepository colorRepository;

    private final ModelMapper modelMapper;
    public ColorDto toColorDto(Color color){

        ColorDto colorDto = modelMapper.map(color,ColorDto.class);
        return colorDto;
    }
}
