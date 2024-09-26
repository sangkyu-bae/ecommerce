package com.example.demo.application.service;

import com.example.demo.application.port.in.command.RefreshTokenCommand;
import com.example.demo.application.port.out.AuthUserUseCase;
import com.example.demo.application.port.out.FindMemberPort;
import com.example.demo.infra.redis.RedisService;
import com.example.demo.infra.security.dto.Result;
import com.example.demo.infra.security.provider.JwtTokenProvider;
import com.example.demo.infra.security.refreshToken.RefreshTokenService;
import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@UseCase
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthUserService implements AuthUserUseCase {

    private final JwtTokenProvider jwtTokenProvider;

    private final FindMemberPort findMemberPort;

    private final RedisService redisService;

    private final RefreshTokenService refreshTokenService;
    @Override
    public Result refreshJwtTokenByRefreshToken(RefreshTokenCommand command) {

        String refreshToken = command.getRefreshToken();
        boolean isValid = jwtTokenProvider.validateJwtToken(refreshToken);

        if(!isValid){
            //예외처리
        }

        String userId = jwtTokenProvider.getUserId(refreshToken);
        boolean isExistRefreshToken = refreshTokenService.existRefreshToken(userId,refreshToken);
        if(!isExistRefreshToken){
            //예외처리
        }

        long memberId =  Long.parseLong(refreshToken);
        Member member = findMemberPort.findMemberByMemberId(new MemberVo.MemberUserId(memberId));
        String userKey = String.valueOf(member.getUserId());

        List<String> roles = member.getRoles();

        String accessToken = jwtTokenProvider.createJwtAccessToken(userKey, command.getUri(), roles);
        Date expiredTime = jwtTokenProvider.getExpiredTime(accessToken);

        Map<String, Object> tokens = Map.of(
                "accessToken", accessToken,
                "accessExpiredTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiredTime)
        );

        return Result.createSuccessResult(tokens);
    }

    @Override
    public String removeJwtTokenByAccessToken(String jwtToken) {
        try{
            String userId = jwtTokenProvider.getUserId(jwtToken);
            redisService.deleteValues(userId);
        }catch (Exception e){
            return "fail";
        }

        return "success";
    }
}
