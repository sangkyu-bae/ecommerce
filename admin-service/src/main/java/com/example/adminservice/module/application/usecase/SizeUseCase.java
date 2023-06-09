package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.common.method.CommonMethod;
import com.example.adminservice.module.domain.category.dto.CategoryDto;
import com.example.adminservice.module.domain.size.dto.SizeDto;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.service.SizeReadService;
import com.example.adminservice.module.domain.size.service.SizeWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SizeUseCase {
    private final SizeReadService sizeReadService;
    private final SizeWriteService sizeWriteService;
    private final CommonMethod commonMethod;

    public SizeDto createSizeExecute(SizeDto sizeDto){
        Size size = sizeWriteService.createSize(sizeDto);
        SizeDto createSizeDto = sizeReadService.toSizeDto(size);

        return createSizeDto;
    }

    public List<SizeDto> readAllSizeDtoExecute(){
        var sizeList = sizeReadService.readAllSize();
        var sizeDtoList = sizeList.stream().
                map(size -> commonMethod.toDto(size,SizeDto.class)).collect(Collectors.toList());
        return sizeDtoList;
    }
}
