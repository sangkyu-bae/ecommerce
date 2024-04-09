package com.example.adminservice.application.service.color;

import com.example.adminservice.adapter.out.persistence.color.ColorMapper;
import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;
import com.example.adminservice.application.port.in.usecase.color.FindColorUseCase;
import com.example.adminservice.application.port.out.color.FindColorPort;
import com.example.adminservice.domain.Color;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class FindColorService implements FindColorUseCase {


    private final FindColorPort findColorPort;

    private final ColorMapper colorMapper;
    @Override
    public List<Color> findColorAll() {
        List<ColorEntity> colorEntityList = findColorPort.findColorEntityAll();

        return colorEntityList.stream()
                .map(colorEntity -> colorMapper.mapToColor(colorEntity))
                .collect(Collectors.toList());
    }
}
