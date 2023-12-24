package com.example.demo.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Builder @Data
@NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class RegisterMemberCommand extends SelfValidating<RegisterMemberCommand> {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 2,max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]{2,20}")
    private String name;

    @NotBlank
    @Length(min = 8,max=20)
    private String password;

    @NotBlank
    private String confirmPassword;


    public RegisterMemberCommand(String email, String name, String password, String confirmPassword){
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.validateSelf();
    }
}
