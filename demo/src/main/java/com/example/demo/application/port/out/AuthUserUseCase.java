package com.example.demo.application.port.out;
import com.example.demo.application.port.in.command.RefreshTokenCommand;
import com.example.demo.infra.security.dto.Result;

public interface AuthUserUseCase {
    Result refreshJwtTokenByRefreshToken(RefreshTokenCommand command);
}
