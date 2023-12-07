package com.example.adminservice.application.port.in.product;

import com.example.adminservice.adapter.in.request.RegisterColorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterProductComponentCommand {
    private long id;
    @NotNull
    private RegisterColorRequest color;

    @NotNull
    private Set<RegisterSizeCommand> sizes;
}
