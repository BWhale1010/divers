package com.bw.divers.board;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@PostMapping("/board/write")
	public int boardWrite(@RequestParam HashMap<String, Object> params, HttpSession session){
		logger.info("게시글 저장 컨트롤러");
		int user_num = (int) session.getAttribute("user_num");
		params.put("user_num", user_num);
		int success = boardService.boardWrite(params);
		
		
		return success;
	}
	
	@PostMapping("/board/listAdd")
	public HashMap<String, Object> listAdd(int page, int category) {
		logger.info("page : "+page);
		
		return boardService.listAdd(page, category);
	}
}
