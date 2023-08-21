package com.example.adminservice.module.common;

import java.util.List;

public interface CRUDReadService<T> {

    T read(long id);

    List<T> readAll();

}
