package com.example.demo.module.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class User {

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
    

}
