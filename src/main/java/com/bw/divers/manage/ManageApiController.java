package com.bw.divers.manage;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		logger.info("유저 리스트 조회 컨트롤러");
		
		return manageService.listAdd(page, search_username, sort, direction);
	}
	
	@PostMapping("/manage/userRole")
	public int userRole(@RequestParam HashMap<String, Object> param) {
		logger.info("유저 권한 변경 컨트롤러");
		int result = 0;
		
		result = manageService.userRole(param);
		
		return result;
	}
	
	@PostMapping("/manage/userState")
	public int userState(@RequestParam int user_num, int state_num) {
		logger.info("유저 상태 변경 컨트롤러");
		int result = 0;
		
		result = manageService.userState(user_num, state_num);
		
		return result;
	}
	
	@PostMapping("/manage/userInfo")
	public HashMap<String, Object> userInfo(@RequestParam int user_num){
		logger.info("유저 정보 조회 컨트롤러");
		
		return manageService.userInfo(user_num);
	}
	
	@PostMapping("/manage/userLog")
	public HashMap<String, Object> userLog(@RequestParam int user_num){
		logger.info("유저 로그 조회 컨트롤러");
		return manageService.userLog(user_num);
	}
	
	@PostMapping("/manage/boardList")
	public HashMap<String, Object> boardList
	(@RequestParam int page, String search_word, String sort, String direction, String state){
		logger.info("게시글 리스트 조회 컨트롤러");
		return manageService.boardList(page, search_word, sort, direction, state);
	}
	
	@PostMapping("/manage/commentList")
	public HashMap<String, Object> commentList(@RequestParam int page, String search_word, String sort, String direction, String state){
		logger.info("댓글 리스트 조회 컨트롤러");
		return manageService.commentList(page, search_word, sort, direction, state);
	}
	
	@PostMapping("/manage/reportInfo")
	public HashMap<String, Object> reportInfo(@RequestParam int post_num){
		logger.info("게시글 신고 정보 조회 컨트롤러");
		return manageService.reportInfo(post_num);
	}
	
	@PostMapping("/manage/commentInfo")
	public HashMap<String, Object> commentInfo(@RequestParam int comment_num){
		logger.info("댓글 신고 정보 조회 컨트롤러");
		return manageService.commentInfo(comment_num);
	}
	
	@PostMapping("/manage/postBlind")
	public int postBlind(@RequestParam int post_num) {
		logger.info("게시글 블라인드 컨트롤러");
		int row = 0;
		
		row = manageService.postBlind(post_num);
		
		return row;
	}
	
	@PostMapping("/manage/commentBlind")
	public int commentBlind(@RequestParam int comment_num) {
		int row = 0;
		row = manageService.commentBlind(comment_num);
		
		return row;
	}
	
	@PostMapping("/manage/postClear")
	public int postClear(@RequestParam int post_num) {
		int row = 0;
		
		row = manageService.postClear(post_num);
		
		return row;
	}
	
	@PostMapping("/manage/commentClear")
	public int commentClear(@RequestParam int comment_num) {
		logger.info("댓글 블라인드 컨트롤러");
		int row = 0;
		row = manageService.commentClear(comment_num);
		
		return row;
	}
	
	@PostMapping("/manage/reportWrite")
	public int reportWrite(@RequestParam HashMap<String, Object> param) {
		logger.info("게시글/댓글 신고 작성 컨트롤러");
		int row = 0;
		
		row = manageService.reportWrite(param);
		
		return row;
	}
	
	@PostMapping("/manage/reportPostCheck")
	public int reportPostCheck(@RequestParam int post_num, int user_num) {
		logger.info("게시글 신고 확인 컨트롤러");
		int row = 0;
		row = manageService.reportPostCheck(post_num, user_num);
		return row;
	}
	
	@PostMapping("/manage/suspUser")
	public int suspUser(@RequestParam int user_num,  HttpServletRequest request, HttpServletResponse response) {
		logger.info("회원 정지 컨트롤러");
		int row = 0;
		row = manageService.suspUser(user_num, request, response);
		
		return row;
	}
	
	@PostMapping("/manage/reportCommentCheck")
	public int reportCommentCheck(@RequestParam int comment_num, int user_num) {
		logger.info("댓글 신고 확인 컨트롤러");
		int row = 0;
		row = manageService.reportCommentCheck(comment_num, user_num);
		return row;
	}

}

