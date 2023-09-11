package com.example.adminservice.application.usecase;

import com.example.adminservice.module.common.method.CommonMethod;
import com.example.adminservice.domain.color.dto.ColorDto;
import com.example.adminservice.domain.color.entity.Color;
import com.example.adminservice.domain.color.service.ColorReadService;
import com.example.adminservice.domain.color.service.ColorWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ColorUseCase {

    private final ColorWriteService colorWriteService;
    private final ColorReadService colorReadService;
    private final CommonMethod commonMethod;

    public ColorDto createColorExecute(ColorDto colorDto){
        Color color = colorWriteService.createColor(colorDto);
        ColorDto toColorDto = colorReadService.toColorDto(color);

        return toColorDto;
    }

    public List<ColorDto> readAllColorDtoExecute(){
        var colorList = colorReadService.readAllColor();
        var colorDtoList = colorList.stream().
                map(color -> commonMethod.toDto(color,ColorDto.class)).collect(Collectors.toList());
        return colorDtoList;
    }


}
