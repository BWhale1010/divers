package com.bw.divers.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionManager {
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

    private static Set<Integer> loggedInUsers = new HashSet<>();
    
    public static void loginUser(int user_num, HttpServletResponse response) {
        loggedInUsers.add(user_num);
        
        // 새로운 세션 쿠키 생성
        Cookie sessionCookie = new Cookie("sessionId", UUID.randomUUID().toString());
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(60 * 60 * 24); // 세션 유효 시간 설정 (예: 1일)
        response.addCookie(sessionCookie);
        
        logger.info("User {} logged in", user_num);
    }
    
    public static void logoutUser(int user_num, HttpServletRequest request, HttpServletResponse response) {
        loggedInUsers.remove(user_num);

        // 세션 만료시키기
        request.getSession().invalidate();
        
        // 세션 쿠키 삭제하기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionId")) {
                    cookie.setMaxAge(0); // 쿠키 만료시간을 0으로 설정하여 삭제
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        // 로그아웃 후 홈으로 리다이렉트
        try {
            response.sendRedirect("/"); // 홈으로 리다이렉트
        } catch (IOException e) {
            // 리다이렉트 중 예외 발생 시 처리할 내용
            logger.error("Redirect to home failed: {}", e.getMessage());
        }
        
        logger.info("User {} logged out", user_num);
    }
    
    public static boolean isUserLoggedIn(int user_num) {
    	logger.info("Find {} login", user_num);	
        return loggedInUsers.contains(user_num);
    }
}
