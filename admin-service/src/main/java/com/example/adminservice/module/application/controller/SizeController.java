package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.SizeUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.size.dto.SizeDto;
import com.example.adminservice.module.domain.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SizeController {
    private final SizeRepository sizeRepository;

    private final SizeUseCase sizeUseCase;

    @PostMapping("/admin/size")
    public ResponseEntity<SizeDto> createSize(@Valid @RequestBody SizeDto sizeDto, Errors errors){
        if(errors.hasErrors()){
            throw new CustomException(ErrorCode.SIZE_FORM_NO_VALIDATE,"createSize");
        }

        SizeDto createSize = sizeUseCase.createSizeExecute(sizeDto);
        return ResponseEntity.ok().body(createSize);
    }

    @GetMapping("/admin/sizes")
    public ResponseEntity<List<SizeDto>> readSizeAll(){
        var sizeList = sizeUseCase.readAllSizeDtoExecute();
        return ResponseEntity.ok().body(sizeList);
    }
}
