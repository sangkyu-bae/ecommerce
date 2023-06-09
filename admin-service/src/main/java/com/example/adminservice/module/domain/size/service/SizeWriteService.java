package com.example.adminservice.module.domain.size.service;

import com.example.adminservice.module.domain.size.dto.SizeDto;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SizeWriteService {
    private final SizeRepository sizeRepository;

    private final ModelMapper modelMapper;
    public Size createSize(SizeDto sizeDto){
        Size size = modelMapper.map(sizeDto,Size.class);
        return sizeRepository.save(size);
    }
}
