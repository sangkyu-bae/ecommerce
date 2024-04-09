package com.example.adminservice.adapter.in.web.color;

import com.example.adminservice.application.port.in.usecase.color.FindColorUseCase;
import com.example.adminservice.domain.Color;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class FindColorController {

    private final FindColorUseCase findColorUseCase;

    @Operation(summary = "find color All", description = "모든 컬러 조회")
    @GetMapping("/admin/colors")
    public ResponseEntity<List<Color>> findColorAll(){

        List<Color> colorList = findColorUseCase.findColorAll();
        return ResponseEntity.ok().body(colorList);
    }
}
