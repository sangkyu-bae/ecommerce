package com.example.adminservice.application.port.out.product;

import com.example.adminservice.adapter.out.persistence.entity.BrandEntity;

import java.util.List;

public interface FindBrandPort {

    List<BrandEntity> findBrandAll();
}
