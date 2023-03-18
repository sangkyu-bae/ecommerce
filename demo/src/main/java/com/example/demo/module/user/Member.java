package com.example.demo.module.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long userId;

    private String email;

    private String name;

    private String address;

    @JsonIgnore
    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private String phone;

    private boolean notificationByEmail;

    private LocalDateTime joinAt;

    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    public List<String> getRoles() {
        return this.authorities.stream()
                .map(Authority::getRole)
                .collect(toList());
    }
}
