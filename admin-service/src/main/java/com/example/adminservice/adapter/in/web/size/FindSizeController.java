package com.example.adminservice.adapter.in.web.size;

import com.example.adminservice.application.port.in.usecase.size.FindSizeUseCase;
import com.example.adminservice.domain.SizeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class FindSizeController {

    private final FindSizeUseCase findSizeUseCase;

    @Operation(summary = "find sizeAll", description = "모든 사이즈 조회")
    @GetMapping("/admin/sizes")
    public ResponseEntity<List<SizeVo>> findSizeAll(){
        List<SizeVo> sizeList = findSizeUseCase.findSizeAll();
        return ResponseEntity.ok().body(sizeList);
    }
}
