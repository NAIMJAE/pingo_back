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
        String token = request.getHeader(AUTH_HEADER);
        log.info("token : " + token);
        log.info("doFilterInternal.........22");

        // 토큰 검사
        if (token != null) {

            try {
                jwtProvider.validateToken(token);
                log.info("doFilterInternal.........33");

                // 자동 로그인 체크 (특정 URL 요청일 때만 수행)
                if (path.equals("/auto-signin")) {
                    log.info("자동 로그인 체크 요청 감지...");

                    Claims claims = jwtProvider.getClaims(token);
                    String userNo = (String) claims.get("userNo");
                    String userRole = (String) claims.get("userRole");

                    // JSON 응답 설정
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    // JSON 데이터 생성
                    String jsonResponse = "{ \"data\": { \"message\": \"자동 로그인 성공\", \"userNo\": \"" + userNo + "\", \"userRole\": \"" + userRole + "\" } }";

                    response.getWriter().write(jsonResponse);
                    return;
                }

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
