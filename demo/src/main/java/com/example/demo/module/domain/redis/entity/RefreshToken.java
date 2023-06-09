package com.example.demo.module.domain.redis.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import org.springframework.data.annotation.Id;

@Getter
@RedisHash("refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private String userId;
    private String refreshTokenId;

    public static RefreshToken of(String userId, String refreshTokenId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.userId = userId;
        refreshToken.refreshTokenId = refreshTokenId;
        return refreshToken;
    }

}
