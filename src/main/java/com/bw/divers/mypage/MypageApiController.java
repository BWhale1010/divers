package com.bw.divers.mypage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bw.divers.user.UserDTO;
import com.bw.divers.user.UserService;

@RestController
public class MypageApiController {

	@Autowired MypageService mypageService;
	@Autowired UserService userService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@PutMapping("/mypage/update")
	public int mypageUpdate(@RequestParam String username, String nickname) {
		logger.info("마이페이지 수정 컨트롤러");
		int result = 0;
		
		result = mypageService.mypageUpdate(username, nickname);
	
		return result;
	}
	
	@PostMapping("/mypage/passwordMatch")
	public int passowordMatch(@RequestParam String username, String password) {
		logger.info("기존 비밀번호 확인 컨트롤러");
		
		int result = mypageService.passwordMatch(username, password);
		
		return result;
	}
	
	@PostMapping("/mypage/delProfile")
	public int delProfile(@RequestParam int user_num, HttpSession session) {
		logger.info("프로필 이미지 삭제 컨트롤러");
		int result = 0;
		
		result = mypageService.delProfile(user_num);
		
		return result;
	}




}
