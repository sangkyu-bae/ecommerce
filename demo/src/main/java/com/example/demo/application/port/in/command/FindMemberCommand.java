package com.example.demo.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder @NoArgsConstructor
public class FindMemberCommand extends SelfValidating<FindMemberCommand> {

    @NotBlank
    @NotNull
    @Email
    private String email;

    public FindMemberCommand(String email){
        this.email = email;
        this.validateSelf();
    }
}
