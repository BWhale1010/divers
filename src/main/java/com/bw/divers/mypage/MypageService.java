package com.bw.divers.mypage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@MapperScan("com.bw.divers.mypage")
public class MypageService {
	@Autowired MypageDAO mypageDAO;
	@Autowired PasswordEncoder encoder;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public int mypageUpdate(String username, String nickname) {
		logger.info("마이페이지 수정 서비스");
		
		return mypageDAO.mypageUpdate(username, nickname);
	}

	public int passwordMatch(String username, String password) {
		logger.info("기존 비밀번호 확인 서비스");
		int result = 0;
		String enc_password = mypageDAO.oriPassword(username);
		boolean match = encoder.matches(password, enc_password);
		
		if(match) {
			result = 1;
		}
		
		return result;
	}
	
	public String enc_password(String password) {
		String enc_pw = encoder.encode(password);
		
		return enc_pw;
	}

}
