package com.example.adminservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Color {

    private Long id;

    private String name;

    public static Color createGenerateColor(
            ColorId colorId,
            ColorName colorName
    ) {
        return new Color(
                colorId.getId(),
                colorName.getName()
        );
    }

    @Value
    public static class ColorId {
        Long id;

        public ColorId(long val) {
            this.id = val;
        }
    }

    @Value
    public static class ColorName {
        String name;

        public ColorName(String val) {
            this.name = val;
        }
    }
}
