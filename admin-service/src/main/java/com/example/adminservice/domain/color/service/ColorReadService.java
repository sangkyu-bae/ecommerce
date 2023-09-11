package com.example.adminservice.domain.color.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.domain.color.dto.ColorDto;
import com.example.adminservice.domain.color.entity.Color;
import com.example.adminservice.domain.color.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Color readColor(String colorName){
        Color color = colorRepository.findByName(colorName).orElseThrow(()->new CustomException(ErrorCodet.COLOR_NOT_FOUND,"readColor"));
        return color;
    }

    public List<Color> readAllColor(){
        List<Color> colorList = colorRepository.findAll();
        return colorList;
    }


}
