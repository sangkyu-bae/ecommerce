package com.example.adminservice.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor@AllArgsConstructor
@Data @Getter
public class RegisterSizeCommand {
    private long id;

    @NotNull
    private int size;

    @NotNull
    private int quantity;
}
