package com.bw.divers.user;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class UserApiController {

	@Autowired UserService userService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
@GetMapping("/user/check-username")
	public HashMap<String, Object> usernameCheck(@RequestParam String username) {
		logger.info("아이디 중복확인 컨트롤러 username : "+username);
		
		int row = userService.usernameCheck(username);
		logger.info("row : "+row);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", row);
	
		return map;
	}

@GetMapping("/user/check-email")
public HashMap<String, Object> emailCheck(@RequestParam String email) {
	logger.info("이메일 중복확인 컨트롤러 email : "+email);
	
	int row = userService.emailCheck(email);
	logger.info("row : "+row);
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("success", row);

	return map;
}

@GetMapping("/user/email")
public String mailSend(String email, String func) {
	logger.info("이메일 요청 컨트롤러 : "+email);
	logger.info("이메일 요청 컨트롤러 : "+func);
	
	return userService.mailSend(email, func);
}

@GetMapping("/user/check-nickname")
public HashMap<String, Object> nicknameCheck(@RequestParam String nickname) {
	logger.info("닉네임 중복확인 컨트롤러 email : "+nickname);
	
	int row = userService.nicknameCheck(nickname);
	logger.info("row : "+row);
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("success", row);

	return map;
}

@PostMapping("/user/join")
public HashMap<String, Object> join(@RequestParam HashMap<String, String> params, RedirectAttributes rttr) {
	logger.info("회원가입 컨트롤러");
	
	String msg = userService.regValidate(params);
	HashMap<String, Object> map = new HashMap<String, Object>();
	String state = "";
	
	if(msg != null) {
		logger.info("여기?");
		state = "fail";
		map.put("msg", msg);
		map.put("state", state);
		return map;
	}else {
		int row = userService.join(params);
		state = "success";
		map.put("msg", "회원가입이 완료되었습니다.");
		map.put("success", row);
		map.put("state", state);
		
		return map;
	}
	

}



}
