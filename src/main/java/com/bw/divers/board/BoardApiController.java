package com.bw.divers.board;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("/board/thumbnail/{postNum}")
	public HashMap<String, Object> thumbnail(@PathVariable int postNum) {
		logger.info("썸네일 요청 : "+postNum);
		String thumbnail = boardService.thumbnail(postNum);
		String text = boardService.text(postNum);
		logger.info("thumbnail : "+thumbnail);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("thumbnail", thumbnail);
		map.put("text", text);
		
		return map;
	}
	
	@PostMapping("/board/edit/{postNum}")
	public int boardUpdate(@PathVariable String postNum, @RequestParam HashMap<String, String> param ){
		logger.info("게시글 수정 컨트롤러 : "+ postNum);
		logger.info("title : "+ param.get("title"));
		
		int success = boardService.boardUpdate(postNum, param);
		
		return success;
	}
	
	@PutMapping("/board/delete/{postNum}")
	public int boardDelete(@PathVariable int postNum, @RequestParam int user_num, 
			HttpSession session) {
		logger.info("게시판 삭제 postNum : "+postNum);
		int success = 0;
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.boardDelete(postNum);
		}		
		
		return success;
	}
	
	
	
	
}
