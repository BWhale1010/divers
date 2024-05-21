package com.bw.divers.mypage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bw.divers.manage.ManageService;
import com.bw.divers.user.UserService;

@Service
@MapperScan("com.bw.divers.mypage")
public class MypageService {
	@Autowired MypageDAO mypageDAO;
	@Autowired PasswordEncoder encoder;
	@Autowired UserService userService;
	@Autowired ManageService manageService;
	@Value("${file.location}") private String location;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public int mypageUpdate(String username, String nickname, HttpSession session) {
		logger.info("마이페이지 수정 서비스");
		int result = 0;
		
		int user_num = (int) session.getAttribute("user_num");
		
		int sort_num = 8;
		int alter_num = 3;
		int logResult = manageService.logSystem(user_num, sort_num, alter_num);
		
		if(logResult == 1) {
			result = mypageDAO.mypageUpdate(username, nickname);
		}
		
		return result;
	}

	public int passwordMatch(String user_num, String password) {
		logger.info("기존 비밀번호 확인 서비스");
		int result = 0;
		String enc_password = mypageDAO.oriPassword(user_num);
		boolean match = encoder.matches(password, enc_password);
		
		if(match) {
			result = 1;
		}
		
		return result;
	}
	
	public String enc_password(String password) {
		logger.info("비밀번호 해시 서비스");
		String enc_pw = encoder.encode(password);
		
		return enc_pw;
	}

	public void profile(MultipartFile profileImg, int user_num) {
		logger.info("프로필 사진 저장 서비스");
		
		int row = mypageDAO.imgCheck(user_num);
		
		if(row>0) {
			int delProfile = mypageDAO.delProfile(user_num);
			logger.info("기존 프로필 제거 : "+delProfile);
		}
		
		
		String oriFileName = profileImg.getOriginalFilename();
		logger.info("oriFileName : "+oriFileName);
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		
		String newFileName = System.currentTimeMillis()+ext;
		
		try {
			byte[] arr = profileImg.getBytes();
			Path path = Paths.get(location+newFileName);
			Files.write(path, arr);
			
			int sort_num = 9;
			int alter_num = 3;
			int logResult = manageService.logSystem(user_num, sort_num, alter_num);
			if(logResult == 1) {
				mypageDAO.addProfile(user_num, oriFileName, newFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int delProfile(int user_num) {
		logger.info("프로필 이미지 삭제 서비스");
		int result = 0;
		int sort_num = 9;
		int alter_num = 3;
		int logResult = manageService.logSystem(user_num, sort_num, alter_num);
		
		if(logResult == 1) {
			result = mypageDAO.delProfile(user_num);
		}
		
		return result;
	}

	public int withDraw(int user_num, HttpServletRequest request, HttpServletResponse response) {
		logger.info("회원 탈퇴 서비스");
		int sort_num = 4;
		int alter_num = 1;
		int logResult = manageService.logSystem(user_num, sort_num, alter_num);
		int state_num = 3;
		manageService.userState(user_num, state_num);
		
		return logResult;
	}

}
