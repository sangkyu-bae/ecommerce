package com.example.demo.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtEntryPoint jwtEntryPoint;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
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
        http
                .cors()

                .and()
                .csrf().disable()
                .authorizeRequests() // 5
                .antMatchers("/", "/join/**", "/login", "/health").permitAll()
                .anyRequest().hasRole("USER")

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)

                .and()
                .logout().disable() // 6
                .sessionManagement().sessionCreationPolicy(STATELESS)

                .and() // 7
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
