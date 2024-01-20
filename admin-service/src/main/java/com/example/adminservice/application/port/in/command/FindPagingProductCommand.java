package com.example.adminservice.application.port.in.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPagingProductCommand {
    private final int pageNum;
}
