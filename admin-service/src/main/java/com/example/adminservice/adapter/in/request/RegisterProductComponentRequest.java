package com.example.adminservice.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductComponentRequest {
    private long id;

    private RegisterColorRequest color;

    private Set<RegisterSizeRequest> sizes;
}
