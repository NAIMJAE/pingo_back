//package com.pingo.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import com.pingo.util.JwtTokenProvider;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//@Component
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//
//    // 스프링 시큐리티도 구현해서 jwt토큰이랑 연결해주세요~
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        super(authenticationManager -> null); // 기본 인증 매니저 설정 제거
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String token = jwtTokenProvider.resolveToken(request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            var auth = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        chain.doFilter(request, response);
//    }
//}
