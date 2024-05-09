package com.bw.divers.manage;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageApiController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ManageService manageService;
	
	@PostMapping("/manage/listAdd")
	public HashMap<String, Object> listAdd(@RequestParam int page, String search_username){
		logger.info("유저리스트 컨트롤러");
		
		return manageService.listAdd(page, search_username);
	}

}
