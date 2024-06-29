package com.example.order.domain;

import lombok.Data;

@Data
public class TypeEnumMapper {

    private int status;
    private String name;

    public TypeEnumMapper(EnumMapperType mapperType){
        this.status = mapperType.getStatus();
        this.name = mapperType.getName();
    }
}
