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
	public HashMap<String, Object> listAdd(@RequestParam int page, String search_username, String sort, String direction){
		logger.info("유저리스트 컨트롤러");
		
		return manageService.listAdd(page, search_username, sort, direction);
	}
	
	@PostMapping("/manage/userRole")
	public int userRole(@RequestParam HashMap<String, Object> param) {
		int result = 0;
		
		result = manageService.userRole(param);
		
		return result;
	}
	
	@PostMapping("/manage/userState")
	public int userState(@RequestParam HashMap<String, Object> param) {
		int result = 0;
		
		result = manageService.userState(param);
		
		return result;
	}
	
	@PostMapping("/manage/userInfo")
	public HashMap<String, Object> userInfo(@RequestParam int user_num){
		
		return manageService.userInfo(user_num);
	}
	
	@PostMapping("/manage/userLog")
	public HashMap<String, Object> userLog(@RequestParam int user_num){
		
		return manageService.userLog(user_num);
	}
	
	@PostMapping("/manage/userBoard")
	public HashMap<String, Object> userBoard(@RequestParam int user_num){
		
		return manageService.userBoard(user_num);
	}


}
