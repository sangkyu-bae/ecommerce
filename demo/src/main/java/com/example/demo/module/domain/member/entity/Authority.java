package com.example.demo.module.domain.member.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter@Setter@EqualsAndHashCode(of="id")
@NoArgsConstructor @AllArgsConstructor @Builder
public class Authority implements GrantedAuthority {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private Member member;
    private String role;

    public static Authority ofUser(Member member) {
        return Authority.builder()
                .role("ROLE_USER")
                .member(member)
                .build();
    }

    public static Authority ofAdmin(Member member) {
        return Authority.builder()
                .role("ROLE_ADMIN")
                .member(member)
                .build();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
