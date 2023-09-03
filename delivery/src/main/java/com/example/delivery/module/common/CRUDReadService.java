package com.example.delivery.module.common;

import java.util.List;

public interface CRUDReadService <T>{

    T read(Long id);

    List<T> readAll();
}
