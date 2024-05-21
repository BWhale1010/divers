package com.bw.divers.board;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		int result  = boardService.boardWrite(params);
		
		return result;
	}
	
	@PostMapping("/board/listAdd")
	public HashMap<String, Object> listAdd(int page, int category, String search_word) {
		logger.info("게시글 리스트 정보 조회 컨트롤러");
		
		return boardService.listAdd(page, category, search_word);
	}
	
	@PostMapping("/board/thumbnail/{postNum}")
	public HashMap<String, Object> thumbnail(@PathVariable int postNum) {
		logger.info("게시글 리스트 썸네일 요청 : "+postNum);
		String thumbnail = boardService.thumbnail(postNum);
		String text = boardService.text(postNum);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("thumbnail", thumbnail);
		map.put("text", text);
		
		return map;
	}
	
	@PostMapping("/board/edit/{postNum}")
	public int boardUpdate(@PathVariable String postNum, @RequestParam HashMap<String, String> param ){
		logger.info("게시글 수정 컨트롤러 : "+ postNum);
		
		int success = boardService.boardUpdate(postNum, param);
		
		return success;
	}
	
	@PutMapping("/board/delete/{postNum}")
	public int boardDelete(@PathVariable int postNum, @RequestParam int user_num, 
			HttpSession session) {
		logger.info("게시판 삭제 컨트롤러 : "+postNum);
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
		logger.info("댓글 리스트 컨트롤러 : "+post_num);
		
		return boardService.commentList(page, post_num, session);
	}
	
	@PutMapping("/comment/delete/{comment_num}")
	public int commentDelete(@PathVariable int comment_num, @RequestParam int user_num, HttpSession session) {
		logger.info("댓글 삭제 컨트롤러 : "+comment_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.commentDelete(comment_num);
		}
		
		return success;
	}
	
	@PutMapping("/comment/edit/{comment_num}")
	public int commentEdit(@PathVariable int comment_num, @RequestParam String comment, int user_num, HttpSession session) {
		logger.info("댓글 수정 컨트롤러 : "+comment_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.commentEdit(comment_num, comment);
		}
		
		return success;
	}
	
	@PutMapping("/board/thumb/{postNum}")
	public int postThumb(@PathVariable int postNum, @RequestParam int user_num, HttpSession session) {
		logger.info("게시글 추천 컨트롤러 postNum : "+postNum);
		logger.info("게시글 추천 컨트롤러 user_num : "+user_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.postThumb(postNum, user_num);
		}
		
		return success;
	}
	
	@PostMapping("/comment/thumb/{comment_num}")
	public int commentThumb(@PathVariable int comment_num, @RequestParam int user_num, HttpSession session) {
		logger.info("댓글 추천 컨트롤러 comment_num : "+comment_num);
		logger.info("댓글 추천 컨트롤러 user_num : "+user_num);
		int success = 0;
		
		int login_userNum = (int) session.getAttribute("user_num");
		
		if(login_userNum == user_num) {
			success = boardService.commentThumb(comment_num, user_num);
		}
		
		return success;
	}
	
	@PostMapping("/board/recommendList")
	public HashMap<String, Object> recommendList(){
		logger.info("사이드 바 추천 리스트 컨트롤러");
		return boardService.sideRecommend();
	}
	
	@PostMapping("/board/popList")
	public HashMap<String, Object> popList(){
		logger.info("사이드 바 인기 리스트 컨트롤러");
		return boardService.sidePop();
	}
	
	@PostMapping("/board/newList")
	public HashMap<String, Object> newList(){
		logger.info("사이드 바 최신 리스트 컨트롤러");
		return boardService.sideNew();
	}



	
	
}
