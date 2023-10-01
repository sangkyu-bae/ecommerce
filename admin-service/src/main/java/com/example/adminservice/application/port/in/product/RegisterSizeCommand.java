package com.example.adminservice.application.port.in.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor@AllArgsConstructor
@Data
public class RegisterSizeCommand {
    private long id;

    @NotNull
    private int size;

    @NotNull
    private int quantity;
}
