package com.example.adminservice.application.port.out.color;

import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;

import java.util.List;

public interface FindColorPort {
    List<ColorEntity> findColorEntityAll();
}
