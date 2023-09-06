package com.example.order.module.common.method;

public interface CRUDWriteService <T,V>{

    void delete(Long id);

    boolean deleteAll();

    T update(T domain);

    T create(V domain);
}
