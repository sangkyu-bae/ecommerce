package com.example.order.module.common.method;

import java.util.List;

public interface CRUDReadService <T,V>{

    T read(Long id);

    List<T>readAll();

    T toEntity(V v);
}
