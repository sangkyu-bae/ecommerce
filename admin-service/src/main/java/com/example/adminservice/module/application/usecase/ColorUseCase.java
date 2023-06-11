package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.domain.color.dto.ColorDto;
import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.color.service.ColorReadService;
import com.example.adminservice.module.domain.color.service.ColorWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ColorUseCase {

    private final ColorWriteService colorWriteService;
    private final ColorReadService colorReadService;

    public ColorDto createColorExecute(ColorDto colorDto){
        Color color = colorWriteService.createColor(colorDto);
        ColorDto toColorDto = colorReadService.toColorDto(color);

        return toColorDto;
    }
}
