package com.example.adminservice.adapter.in.web.brand;

import com.example.adminservice.application.port.in.usecase.brand.FindBrandUseCase;
import com.example.adminservice.domain.Brand;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class FindBrandController {

    private final FindBrandUseCase findBrandUseCase;

    @Operation(summary = "find brandAll", description = "모든 브랜드 조회")
    @GetMapping("/admin/brands")
    public ResponseEntity<List<Brand>> findBrandAll(){

        List<Brand> brandList = findBrandUseCase.findBrandAll();
        return ResponseEntity.ok().body(brandList);
    }
}
