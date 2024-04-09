package com.example.adminservice.application.port.in.usecase.color;

import com.example.adminservice.domain.Color;

import java.util.List;

public interface FindColorUseCase {
    List<Color> findColorAll();
}
