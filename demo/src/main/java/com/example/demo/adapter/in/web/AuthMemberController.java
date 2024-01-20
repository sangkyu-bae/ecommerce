package com.example.demo.adapter.in.web;

import com.example.demo.adapter.in.request.RefreshTokenRequest;
import com.example.demo.application.port.in.command.RefreshTokenCommand;
import com.example.demo.application.port.out.AuthUserUseCase;
import com.example.demo.infra.security.dto.Result;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AuthMemberController {
    private final AuthUserUseCase authUserUseCase;

    @PostMapping(path = "/user/refresh-token")
    ResponseEntity<Result> refreshToken(@RequestBody RefreshTokenRequest request,
                                        HttpServletRequest httpRequest) {
        RefreshTokenCommand command = RefreshTokenCommand.builder()
                .refreshToken(request.getRefreshToken())
                .uri(httpRequest.getRequestURI())
                .build();

        Result refreshToken = authUserUseCase.refreshJwtTokenByRefreshToken(command);

        return ResponseEntity.ok().body(refreshToken);
    }
}
