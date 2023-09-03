package com.example.delivery.module.common;

public interface CRUDWriteService <T,V>{
    boolean delete(Long id);

    boolean deleteAll();

    T update(T domain);

    T create(V domain);
}
