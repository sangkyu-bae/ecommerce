package com.example.demo.infra.security.refreshToken;

import org.springframework.stereotype.Service;

public interface RefreshTokenService {
    void updateRefreshToken(String email, String uuid);

    boolean existRefreshToken(String userId,String refreshToken);
}
