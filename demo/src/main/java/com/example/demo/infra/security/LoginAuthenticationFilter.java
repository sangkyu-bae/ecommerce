package com.example.demo.infra.security;

import com.example.demo.infra.redis.RedisService;
import com.example.demo.infra.security.dto.LoginRequest;
import com.example.demo.infra.security.dto.Result;
import com.example.demo.infra.security.provider.CookieProvider;
import com.example.demo.infra.security.provider.JwtTokenProvider;
import com.example.demo.infra.security.refreshToken.RefreshTokenServiceImpl;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.respository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenServiceImpl refreshTokenServiceImpl;
    private final CookieProvider cookieProvider;

    private final RedisService redisService;

    private final MemberRepository memberRepository;


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = null;;
//        LoginRequest credential=new ObjectMapper().readValue(request.getInputStream(),LoginRequest.class);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginRequest credential = new LoginRequest(email,password);

        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credential.getEmail(), credential.getPassword())
            );
        }catch (BadCredentialsException e){
            System.out.println("잘못된 비밀번호 및 접근");
        }

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String userId = user.getUsername();

        Member member = memberRepository.findByEmail(user.getUsername()).orElseThrow();
        String userKey = String.valueOf(member.getUserId());

        // response body에 넣어줄 access token 및 expired time 생성
//        String accessToken = jwtTokenProvider.createJwtAccessToken(userId, request.getRequestURI(), roles);
        String accessToken = jwtTokenProvider.createJwtAccessToken(userKey, request.getRequestURI(), roles);
        Date expiredTime = jwtTokenProvider.getExpiredTime(accessToken);
        // 쿠키에 넣어줄 refresh token 생성
        String refreshToken = jwtTokenProvider.createJwtRefreshToken();

        // redis에 새로 발행된 refresh token 값 갱신
        refreshTokenServiceImpl.updateRefreshToken(userId, jwtTokenProvider.getRefreshTokenId(refreshToken));

        // 쿠키 설정
        ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(refreshToken);

        Cookie cookie = cookieProvider.of(refreshTokenCookie);

        response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");
        response.setContentType(APPLICATION_JSON_VALUE);
        response.addCookie(cookie);

        // body 설정
        Map<String, Object> tokens = Map.of(
                "accessToken", accessToken,
                "accessExpiredTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiredTime)
        );

        new ObjectMapper().writeValue(response.getOutputStream(), Result.createSuccessResult(tokens));
    }
}
