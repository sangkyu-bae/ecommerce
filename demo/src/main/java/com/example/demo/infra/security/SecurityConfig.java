package com.example.demo.infra.security;

import com.example.demo.infra.redis.RedisService;
import com.example.demo.infra.security.provider.CookieProvider;
import com.example.demo.infra.security.provider.JwtTokenProvider;
import com.example.demo.infra.security.refreshToken.RefreshTokenServiceImpl;
import com.example.demo.module.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenServiceImpl refreshTokenServiceImpl;
    private final CookieProvider cookieProvider;
    private final RedisService redisService;
    private PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception{
//        return super.authenticationManagerBean();
//    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/favicon.ico");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Custom Login Authentication 필터를 사용함
        LoginAuthenticationFilter loginAuthenticationFilter =
                new LoginAuthenticationFilter(authenticationManagerBean(), jwtTokenProvider, refreshTokenServiceImpl, cookieProvider, redisService,memberRepository);
        loginAuthenticationFilter.setFilterProcessesUrl("/user/login");

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().anyRequest().permitAll();

        http.addFilter(loginAuthenticationFilter);
    }

}
