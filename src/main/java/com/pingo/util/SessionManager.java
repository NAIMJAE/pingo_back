package com.pingo.util;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    // 🔥 세션을 저장할 Map (동시 접근을 고려하여 ConcurrentHashMap 사용)
    private static final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    // 세션을 저장 (이메일 인증 요청 시 호출)
    public static void addSession(HttpSession session) {
        sessionMap.put(session.getId(), session);
    }

    // 세션 ID로 세션 가져오기 (클라이언트가 sessionId 보내면 사용)
    public static HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    // 세션 삭제 (로그아웃 시 사용)
    public static void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }
}
