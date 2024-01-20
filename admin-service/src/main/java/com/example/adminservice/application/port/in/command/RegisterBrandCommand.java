package com.example.adminservice.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterBrandCommand {

    @NotNull
    Long id;

    @NotNull
    String name;
}
