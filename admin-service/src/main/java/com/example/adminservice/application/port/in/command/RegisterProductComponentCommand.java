package com.example.adminservice.application.port.in.command;

import com.example.adminservice.adapter.in.request.RegisterColorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data @Getter
public class RegisterProductComponentCommand {
    private long id;
    @NotNull
    private RegisterColorRequest color;

    @NotNull
    private Set<RegisterSizeCommand> sizes;
}
