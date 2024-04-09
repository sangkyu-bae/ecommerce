package com.example.adminservice.adapter.out.persistence.color;

import com.example.adminservice.adapter.out.persistence.entity.ColorEntity;
import com.example.adminservice.adapter.out.persistence.repository.ColorEntityRepository;
import com.example.adminservice.application.port.out.color.FindColorPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;

import java.util.List;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class ColorPersistenceAdapter implements FindColorPort {

    private final ColorEntityRepository colorEntityRepository;
    @Override
    public List<ColorEntity> findColorEntityAll() {
        return colorEntityRepository.findAll();
    }
}
