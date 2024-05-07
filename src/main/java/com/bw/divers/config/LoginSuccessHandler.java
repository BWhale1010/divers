package com.bw.divers.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bw.divers.user.UserDTO;
import com.bw.divers.user.UserService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private HttpSession session;
	@Autowired UserService userService;
	
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
    	String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserDTO userDTO = userService.getUserbyUsername(username);
    	
    	session = request.getSession();
    	int user_num = userDTO.getUser_num();
    	int role_num = userDTO.getRole_num();
    	String user_name = userDTO.getUsername();
    	String new_filename = userDTO.getNew_filename();
    	
    	session.setAttribute("user_num", user_num);
    	session.setAttribute("username", user_name);
    	session.setAttribute("role_num", role_num);
    	session.setAttribute("new_filename", new_filename);
    	
    	String targetUrl = determineTargetUrl(request, response, authentication);
        if (response.isCommitted()) {
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 쿠키에서 이전 페이지 URL 가져오기
        Cookie[] cookies = request.getCookies();
        String redirectUrl = "/";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	logger.info("cookie 이름 : "+cookie.getName());
            	logger.info("쿠키 값 : "+ cookie.getValue());
                if ("visitedPage".equals(cookie.getName())) {
                    // Base64 디코딩하여 원래 URL로 변환
                    String encodedUrl = cookie.getValue();
                    String decodedUrl;
					try {
						decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
	                    logger.info("쿠키 값 : "+ decodedUrl);
	                    redirectUrl = decodedUrl;
	                    
	                    cookie.setValue("");
	                    cookie.setPath("/");
	                    cookie.setMaxAge(0);
	                    response.addCookie(cookie);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    break;
                }
            }
        }
        logger.info("리다이렉트 : "+redirectUrl);
        return redirectUrl;
    }




}
