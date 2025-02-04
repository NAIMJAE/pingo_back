package com.pingo.security.jwt;

import com.pingo.entity.users.Users;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal.........11");
        // 요청 주소에서 마지막 문자열 추출
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String path = uri.substring(i);

        // 토큰 추출
        String header = request.getHeader(AUTH_HEADER);
        String token = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            token = header.substring(TOKEN_PREFIX.length());
        }
        log.info("doFilterInternal.........22");

        // 토큰 검사
        if (token != null) {

            try {
                jwtProvider.validateToken(token);
                log.info("doFilterInternal.........33");

                // refresh 요청일 경우(새로운 access token 발급 요청)
                if (path.equals("/refresh")) {
                    log.info("doFilterInternal.........44");

                    Claims claims = jwtProvider.getClaims(token);
                    String userNo = (String) claims.get("userNo");
                    String userRole = (String) claims.get("userRole");

                    Users users = Users.builder()
                            .userNo(userNo)
                            .userRole(userRole)
                            .build();

                    String accessToken = jwtProvider.createToken(users, 1);

                    response.setStatus(HttpServletResponse.SC_CREATED);
                    response.getWriter().println(accessToken);
                    return;
                }

            } catch (JwtMyException e) {
                e.sendResponseError(response);
                return;
            }
            log.info("doFilterInternal.........55");

            // 시큐리티 인증 처리
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("doFilterInternal.........66");

        }
        log.info("doFilterInternal.........77");

        // 다음 필터 이동
        filterChain.doFilter(request, response);
    }
}
