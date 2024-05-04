package com.bw.divers.mypage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MapperScan("com.bw.divers.mypage")
public class MypageService {
	@Autowired MypageDAO mypageDAO;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public int mypageUpdate(String username, String nickname) {
		logger.info("마이페이지 수정 서비스");
		
		return mypageDAO.mypageUpdate(username, nickname);
	}

}
