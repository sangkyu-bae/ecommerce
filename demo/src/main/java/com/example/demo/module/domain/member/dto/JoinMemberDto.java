package com.example.demo.module.domain.member.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class JoinMemberDto {

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
}
