package com.bw.divers.mypage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MypageApiController {

	@Autowired MypageService mypageService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@PutMapping("/mypage/update")
	public int mypageUpdate(@RequestParam String username, String nickname) {
		logger.info("마이페이지 수정 컨트롤러");
		int result = 0;
		
		result = mypageService.mypageUpdate(username, nickname);
	
		return result;
	}




}
