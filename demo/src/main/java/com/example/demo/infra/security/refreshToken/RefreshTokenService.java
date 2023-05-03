package com.example.demo.infra.security.refreshToken;

import org.springframework.stereotype.Service;

public interface RefreshTokenService {
    void updateRefreshToken(Long id, String uuid);
}
