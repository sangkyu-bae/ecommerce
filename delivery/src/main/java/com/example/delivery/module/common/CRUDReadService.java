package com.example.delivery.module.common;

import java.util.List;

public interface CRUDReadService <T,V>{

    T read(Long id);

    List<T> readAll();

    T toEntity(V dto);

}
