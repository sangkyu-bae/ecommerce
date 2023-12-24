package com.example.order.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    private Long userId;

    private String email;

    private String name;

    private String address;

    private boolean emailVerified;

    private String emailCheckToken;

    private String phone;

    private boolean notificationByEmail;

    private LocalDateTime joinAt;

    private LocalDateTime updateAt;

    private String aggregateIdentifier;

}
