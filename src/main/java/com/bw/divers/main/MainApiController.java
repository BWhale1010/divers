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
		logger.info("메인 추천 리스트 컨트롤러");
		return boardService.recommendList();
	}
	
	@PostMapping("/main/newList")
	public HashMap<String, Object> newList(){
		logger.info("메인 최신 리스트 컨트롤러");
		return boardService.newList();
	}
	
	@PostMapping("/main/popList")
	public HashMap<String, Object> popList(){
		logger.info("메인 인기 리스트 컨트롤러");
		return boardService.popList();
	}

}
