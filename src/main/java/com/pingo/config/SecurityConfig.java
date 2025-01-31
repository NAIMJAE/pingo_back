/*
package com.pingo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Spring Security 설정 파일임을 나타내는 어노테이션
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // CSRF 보호 기능 비활성화 (REST API에서는 일반적으로 CSRF를 사용하지 않음)
                // Flutter와 같은 프론트엔드에서 요청을 보낼 때 CSRF 토큰이 없기 때문에 비활성화해야 함

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 세션을 사용하지 않도록 설정 (JWT 기반 인증을 사용하기 때문에 필요)
                // STATELESS 모드에서는 로그인 시 세션을 생성하지 않음

                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/auth/**").permitAll()
                                // "/api/auth/**" 경로로 들어오는 요청은 인증 없이 접근 가능하도록 설정 (로그인, 회원가입 등)

                                .anyRequest().authenticated()
                        // 그 외의 모든 요청은 인증된 사용자만 접근 가능
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // 비밀번호를 암호화하기 위한 BCryptPasswordEncoder 사용
        // 사용자가 입력한 비밀번호를 DB의 해시된 비밀번호와 비교할 때 필요
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
        // AuthenticationManager는 인증을 담당하는 핵심 컴포넌트
        // Spring Security에서 로그인 시 사용자의 아이디와 비밀번호를 검증하는 역할을 함
    }
}
*/
