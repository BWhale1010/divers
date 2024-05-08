package com.bw.divers.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public HashMap<String, Object> listAdd(int page, int category, String search_word) {
		logger.info("page : "+page);
		logger.info("category : "+category);
		logger.info("search_word : "+search_word);
		
		return boardService.listAdd(page, category, search_word);
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
	
	@PostMapping("/comment/write")
	public int commentWrite( @RequestParam HashMap<String, Object> params, HttpSession session) {
		logger.info("댓글 작성 컨트롤러");
		int success = 0;
		
		String user_num = (String) params.get("user_num");
		logger.info("user_num : "+user_num);

		String login_userNum = String.valueOf((int)session.getAttribute("user_num"));
		logger.info("login_userNum : "+login_userNum);
		
		if(login_userNum.equals(user_num)) {
			success = boardService.commentWrite(params);
		}
		
		return success;
	}
	
	@PostMapping("/comment/list")
	public HashMap<String, Object> commentList(@RequestParam int page, int post_num, HttpSession session){
		logger.info("댓글 리스트 post_num : "+post_num);
		
		return boardService.commentList(page, post_num, session);
	}
	
	@PutMapping("/comment/delete/{comment_num}")
	public int commentDelete(@PathVariable int comment_num, @RequestParam int user_num, HttpSession session) {
		logger.info("comment_num : "+comment_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.commentDelete(comment_num);
		}
		
		return success;
	}
	
	@PutMapping("/comment/edit/{comment_num}")
	public int commentEdit(@PathVariable int comment_num, @RequestParam String comment, int user_num, HttpSession session) {
		logger.info("comment_num : "+comment_num);
		logger.info("comment : "+comment);
		logger.info("user_num : "+user_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.commentEdit(comment_num, comment);
		}
		
		return success;
	}
	
	@PutMapping("/board/thumb/{postNum}")
	public int postThumb(@PathVariable int postNum, @RequestParam int user_num, HttpSession session) {
		logger.info("게시글 추천 postNum : "+postNum);
		logger.info("게시글 추천 user_num : "+user_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.postThumb(postNum, user_num);
		}
		
		return success;
	}
	
	@PostMapping("/comment/thumb/{comment_num}")
	public int commentThumb(@PathVariable int comment_num, @RequestParam int user_num, HttpSession session) {
		logger.info("댓글 추천 comment_num : "+comment_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.commentThumb(comment_num, user_num);
		}
		
		return success;
	}


	
	
}
