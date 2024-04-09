package com.example.adminservice.application.port.in.usecase.brand;

import com.example.adminservice.domain.Brand;

import java.util.List;

public interface FindBrandUseCase {

    List<Brand> findBrandAll();
}
