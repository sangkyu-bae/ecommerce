package com.example.adminservice.application.port.out.size;

import com.example.adminservice.adapter.out.persistence.entity.SizeEntity;
import com.example.adminservice.domain.SizeVo;

public interface FindSizePort {
    SizeEntity findById(SizeVo.SizeId sizeId);
}
