package com.example.adminservice.module.common;

public interface CRUDWriteService <T,V>{
    boolean delete(long id);

    T update(T domain,V updateDomainDto);
}
