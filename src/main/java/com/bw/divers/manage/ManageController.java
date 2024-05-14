package com.bw.divers.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ManageService manageService;
	
	@GetMapping("/manage/userList")
	public String userList() {
		logger.info("유저관리 컨트롤러 ");
		
		return "/manage/userList";		
	}
	
	@GetMapping("/manage/boardList")
	public String boardList() {
		logger.info("게시판 관리 컨트롤러 ");
		
		return "/manage/boardList";		
	}

}
