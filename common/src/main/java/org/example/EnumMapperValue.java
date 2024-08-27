package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EnumMapperValue {
    private int type;

    private String name;

    public EnumMapperValue(EnumMapper enumMapper){
        this.type = enumMapper.getType();
        this.name = enumMapper.getName();
    }
}
