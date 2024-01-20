package com.example.adminservice.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCategoryCommand {

    @NotNull
    Long id;

    @NotNull
    String name;
}
