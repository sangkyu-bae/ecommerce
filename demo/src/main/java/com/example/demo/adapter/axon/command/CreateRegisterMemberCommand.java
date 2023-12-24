package com.example.demo.adapter.axon.command;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
public class CreateRegisterMemberCommand extends SelfValidating<CreateRegisterMemberCommand> {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 2,max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]{2,20}")
    private String name;

    @NotBlank
    private String password;



    public CreateRegisterMemberCommand(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
        this.validateSelf();
    }

}
