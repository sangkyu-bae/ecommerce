package com.example.order.module.common.method;

import java.util.List;

public interface CRUDReadService <T>{

    T read(Long id);

    List<T>readAll();
}
