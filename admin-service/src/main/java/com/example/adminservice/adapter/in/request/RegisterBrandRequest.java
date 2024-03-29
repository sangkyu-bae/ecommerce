package com.example.adminservice.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBrandRequest {
    private Long id;

    private String name;
}
