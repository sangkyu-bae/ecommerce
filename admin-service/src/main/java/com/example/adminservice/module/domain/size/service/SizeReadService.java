package com.example.adminservice.module.domain.size.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.module.domain.size.dto.SizeDto;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SizeReadService {

    private final ModelMapper modelMapper;
    private final SizeRepository sizeRepository;
    public SizeDto toSizeDto(Size size){
        SizeDto sizeDto = modelMapper.map(size, SizeDto.class);
        return sizeDto;
    }

    public Size readSize(int size){
        Size readSize = sizeRepository.findBySize(size).orElseThrow(()->new CustomException(ErrorCodet.SIZE_NOT_FOUND,"readSize"));
        return readSize;
    }

    public List<Size> readAllSize(){
        List<Size> sizeList = sizeRepository.findAll();
        return sizeList;
    }
}
