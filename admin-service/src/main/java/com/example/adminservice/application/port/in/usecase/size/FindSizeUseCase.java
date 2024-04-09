package com.example.adminservice.application.port.in.usecase.size;

import com.example.adminservice.domain.SizeVo;

import java.util.List;

public interface FindSizeUseCase {

    List<SizeVo> findSizeAll();
}
