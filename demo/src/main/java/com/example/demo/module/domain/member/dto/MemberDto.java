package com.example.demo.module.domain.member.dto;

import com.example.demo.module.domain.member.entity.Member;
import lombok.Data;

import java.util.Objects;

@Data
public class MemberDto {

    private Long userId;
    private String email;
    private String name;

    public MemberDto(Member member) {
        this.userId= Objects.requireNonNull(member.getUserId());
        this.email=Objects.requireNonNull(member.getEmail());
        this.name=Objects.requireNonNull(member.getName());
    }

}
