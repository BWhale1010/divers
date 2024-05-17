package com.bw.divers.main;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bw.divers.board.BoardService;

@RestController
public class MainApiController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@PostMapping("/main/recommendList")
	public HashMap<String, Object> recommendList(){
		
		return boardService.recommendList();
	}
	
	@PostMapping("/main/newList")
	public HashMap<String, Object> newList(){
		
		return boardService.newList();
	}
	
	@PostMapping("/main/popList")
	public HashMap<String, Object> popList(){
		
		return boardService.popList();
	}

}
