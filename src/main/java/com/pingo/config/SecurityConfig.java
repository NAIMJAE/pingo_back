package com.pingo.config;

import com.pingo.security.JwtAuthenticationFilter;
import com.pingo.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup").permitAll() // 로그인, 회원가입은 인증 필요 없음
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .addFilter(new JwtAuthenticationFilter(jwtTokenProvider)); // JWT 필터 추가

        return http.build();
    }
}
