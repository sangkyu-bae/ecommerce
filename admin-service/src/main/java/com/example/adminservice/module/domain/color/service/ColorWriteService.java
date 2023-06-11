package com.example.adminservice.module.domain.color.service;

import com.example.adminservice.module.domain.color.dto.ColorDto;
import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.color.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ColorWriteService {

    private final ColorRepository colorRepository;

    private final ModelMapper modelMapper;

    public Color createColor(ColorDto colorDto){
        Color color = modelMapper.map(colorDto,Color.class);
        return colorRepository.save(color);
    }
}
