package com.example.adminservice.adapter.in.web.category;

import com.example.adminservice.application.port.in.usecase.category.FindCategoryUseCase;
import com.example.adminservice.domain.Category;
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
public class FindCategoryController {

    private final FindCategoryUseCase findCategoryUseCase;

    @Operation(summary = "find color All", description = "모든 컬러 조회")
    @GetMapping("/admin/categorys")
    public ResponseEntity<List<Category>> findCategoryAll(){
        List<Category> categoryAllList = findCategoryUseCase.findCategoryAll();
        return ResponseEntity.ok().body(categoryAllList);
    }
}
