package com.example.adminservice.application.port.in.usecase.category;

import com.example.adminservice.domain.Category;

import java.util.List;

public interface FindCategoryUseCase {

    List<Category> findCategoryAll();
}
