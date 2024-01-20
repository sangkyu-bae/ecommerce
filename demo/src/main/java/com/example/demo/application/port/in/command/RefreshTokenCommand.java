package com.example.demo.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RefreshTokenCommand extends SelfValidating<RefreshTokenCommand> {

    @NotNull
    private String refreshToken;
    @NotNull
    private String uri;

    public RefreshTokenCommand(String refreshToken,String uri){
        this.refreshToken = refreshToken;
        this.uri = uri;
        this.validateSelf();
    }
}
