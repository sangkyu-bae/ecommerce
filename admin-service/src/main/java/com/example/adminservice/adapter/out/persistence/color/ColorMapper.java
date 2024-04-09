package com.example.adminservice.adapter.out.persistence.color;

import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;
import com.example.adminservice.domain.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {

    public Color mapToColor(ColorEntity colorEntity){
        return Color.createGenerateColor(
                new Color.ColorId(colorEntity.getId()),
                new Color.ColorName(colorEntity.getName())
        );
    }
}
