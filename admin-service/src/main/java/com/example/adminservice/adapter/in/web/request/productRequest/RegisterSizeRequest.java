package com.example.adminservice.adapter.in.web.request.productRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSizeRequest {
    private long id;

    private int size;

    private int quantity;
}
