package com.example.adminservice.application.port.out.size;

import com.example.adminservice.domain.SizeVo;

public interface UpdateSizePort {
    int updateQuantity(SizeVo.SizeId sizeId,int amount);
}
