package com.bw.divers.user;

import java.sql.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@MapperScan("com.bw.divers.user")
public class UserService {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber;
	
	@Value("${send.email}")
	private String sendEmail;
	
	@Autowired PasswordEncoder encoder;
	
	@Autowired UserDAO userDAO;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public int usernameCheck(String username) {
		logger.info("아이디 중복확인 서비스 username : "+username);
		
		return userDAO.usernameCheck(username);
	}
	public int emailCheck(String email) {
		logger.info("이메일 중복확인 서비스 email : "+email);
		return userDAO.emailCheck(email);
	}
	public int nicknameCheck(String nickname) {
		logger.info("닉네임 중복확인 서비스 nickname : "+nickname);
		return userDAO.nicknameCheck(nickname);
	}
	
	public String mailSend(String email, String func) {
		logger.info("이메일 요청 서비스 email : "+email);
		logger.info("이메일 요청 서비스 func : "+func);
		makeRandomNum();
		String setFrom = sendEmail;
		String toMail = email;
		String title = "";
		String content = "";
		
		if(func.equals("join")) {
			title = "DVIERS 회원가입 인증번호 입니다.";
			content = 					
			"<div style=\"text-align: center;\">" +
                    "<img src=\"cid:logo\">" +
                    "<br><br>" +
                    "<hr style=\"width: 50%;\">" +
                    "<br><br>" +
                    "<p>DVIERS 회원 가입을 환영합니다!</p>" +
                    "<br><br>" +
                    "<p>아래의 인증 번호를 정확하게 입력해 주세요.</p>" +
                    "<br><br>" +
                    "<p style=\"font-size: 24px;\">" + authNumber + "</p>" +
                    "<br><br>" +
                    "<p>본 인증번호를 입력하셔야 회원가입이 가능합니다.</p>" +
                    "<br><br>" +
                    "<hr style=\"width: 50%;\">" +
                    "<br>" +
                    "<p style=\"font-size: 15px;\"> localhoat:8000 </p>"+
                    "<br><br>" +
                    "</div>";
		}else if(func.equals("idFind")) {
			title = "DVIERS 아이디 찾기 인증번호 입니다.";
			content = 					
			"<div style=\"text-align: center;\">" +
                    "<img src=\"cid:logo\">" +
                    "<br><br>" +
                    "<hr style=\"width: 50%;\">" +
                    "<br><br>" +
                    "<p>DVIERS 아이디 찾기 인증 번호입니다.</p>" +
                    "<br><br>" +
                    "<p>아래의 인증 번호를 정확하게 입력해 주세요.</p>" +
                    "<br><br>" +
                    "<p style=\"font-size: 24px;\">" + authNumber + "</p>" +
                    "<br><br>" +
                    "<p>본 인증번호를 입력하셔야 아이디 찾기가 가능합니다.</p>" +
                    "<br><br>" +
                    "<hr style=\"width: 50%;\">" +
                    "<br>" +
                    "<p style=\"font-size: 15px;\"> localhoat:8000 </p>"+
                    "<br><br>" +
                    "</div>";			
		}else {
			title = "DVIERS 비밀번호 재설정 인증번호 입니다.";
			content = 					
			"<div style=\"text-align: center;\">" +
                    "<img src=\"cid:logo\">" +
                    "<br><br>" +
                    "<hr style=\"width: 50%;\">" +
                    "<br><br>" +
                    "<p>DVIERS 비밀번호 재설정 인증 번호입니다.</p>" +
                    "<br><br>" +
                    "<p>아래의 인증 번호를 정확하게 입력해 주세요.</p>" +
                    "<br><br>" +
                    "<p style=\"font-size: 24px;\">" + authNumber + "</p>" +
                    "<br><br>" +
                    "<p>본 인증번호를 입력하셔야 비밀번호 재설정이 가능합니다.</p>" +
                    "<br><br>" +
                    "<hr style=\"width: 50%;\">" +
                    "<br>" +
                    "<p style=\"font-size: 15px;\"> localhoat:8000 </p>"+
                    "<br><br>" +
                    "</div>";		
			
		}
	
		mailSend(setFrom, toMail, title, content);
		
		return Integer.toString(authNumber);
	}
	private void makeRandomNum() {
		Random r = new Random();
		int checkNum = r.nextInt(888888)+111111;
		logger.info("인증번호  : "+checkNum);
		authNumber = checkNum;
	}
	
	private void mailSend(String setFrom, String toMail, String title, String content) {
		logger.info("메일보내기 설정 서비스");
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			
			ClassPathResource image = new ClassPathResource("static/assets/img/divers.png");
			helper.addInline("logo", image);
			
			mailSender.send(message);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public int join(HashMap<String, String> params) {
		logger.info("회원가입 서비스");
		String rowPassword = params.get("password");
		String password = enc_password(rowPassword);
		params.put("password", password);
		
		return userDAO.join(params);	
	}
	
	public String enc_password(String password) {
		logger.info("비밀번호 해시 서비스");
		String enc_pw = encoder.encode(password);
		
		return enc_pw;
	}
	
	public String regValidate(HashMap<String, String> params) {
		logger.info("회원가입 유효성 검사 서비스");
		String msg = null;
		
		Pattern usernamePattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$");
		Pattern passwordPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()\\-_=+{};:,<.>]{6,20}$");
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
		Pattern nicknamePattern = Pattern.compile("^[a-zA-Z0-9가-힣]{2,20}$");
		
		String username = params.get("username");
		String password = params.get("password");
		String email = params.get("email");
		String nickname = params.get("nickname");
		
	    if (username == null || !usernamePattern.matcher(username).matches()) {
	        msg = "유효한 아이디가 아닙니다. 다시 입력하세요.";
	    } else if (password == null || !passwordPattern.matcher(password).matches()) {
	        msg = "유효한 비밀번호가 아닙니다. 다시 입력하세요.";
	    } else if (email == null || !emailPattern.matcher(email).matches()) {
	        msg = "유효한 이메일이 아닙니다. 다시 입력하세요.";
	    } else if (nickname == null || !nicknamePattern.matcher(nickname).matches()) {
	        msg = "유효한 닉네임이 아닙니다. 다시 입력하세요.";
	    }
	    
		return msg;

	}
	public PasswordEncoder passwordEncoder() {
		
		return this.passwordEncoder();
	}
	public UserDTO getUserbyUsername(String username) {
		logger.info("유저찾기 서비스");
		return userDAO.getUserbyUsername(username);
	}
	public String idFind(String email) {
		logger.info("아이디 찾기 서비스");
		return userDAO.idFind(email);
	}
	public String findEmail(String username) {
		logger.info("비밀번호 재설정 이메일 확인 서비스 username : "+username);
		String findEmail = userDAO.findEmail(username);
		
		if(findEmail == null) {
			findEmail = "";
		}
		return findEmail;
	}
	public int pwResetFunc(String username, String password) {
		logger.info("비밀번호 재설정 서비스");
		String enc_password = enc_password(password);
		
		return userDAO.pwResetFunc(username, enc_password);
	}
	
	public Date suspDate(int user_num) {
		logger.info("회원 일시정지 기간 서비스");
		return userDAO.suspDate(user_num);
	}

	public Date withDate(int user_num) {
		logger.info("회원 탈퇴 대기 기간 서비스");
		return userDAO.withDate(user_num);
	}
	
}
