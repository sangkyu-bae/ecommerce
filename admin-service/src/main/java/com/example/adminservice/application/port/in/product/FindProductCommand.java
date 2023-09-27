package com.example.adminservice.application.port.in.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindProductCommand {
    private final Long productId;
}
