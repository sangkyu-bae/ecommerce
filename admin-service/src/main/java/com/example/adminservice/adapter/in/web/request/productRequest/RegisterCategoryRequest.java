package com.example.adminservice.adapter.in.web.request.productRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCategoryRequest {

    private Long id;

    private String name;
}
