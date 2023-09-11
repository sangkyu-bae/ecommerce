package com.example.adminservice.application.controller;

import com.example.adminservice.application.usecase.ColorUseCase;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.domain.color.dto.ColorDto;
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
public class ColorController {

    private final ColorUseCase colorUseCase;

    @PostMapping("/admin/color")
    public ResponseEntity<ColorDto> createColor(@Valid @RequestBody ColorDto colorDto, Errors errors){
        if(errors.hasErrors()){
            throw new CustomException(ErrorCodet.COLOR_FORM_NO_VALIDATE,"createColor");
        }

        ColorDto creatColorDto = colorUseCase.createColorExecute(colorDto);

        return ResponseEntity.ok().body(creatColorDto);
    }

    @GetMapping("/admin/colors")
    public ResponseEntity<List<ColorDto>> readColorAll(){
        var colorList = colorUseCase.readAllColorDtoExecute();
        return ResponseEntity.ok().body(colorList);
    }
}
