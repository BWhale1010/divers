package com.bw.divers.manage;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@MapperScan("com.bw.divers.manage")
@Service
public class ManageService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ManageDAO manageDAO;

	public HashMap<String, Object> listAdd(int page, String search_username, String sort, String direction) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int offset =(page-1)*10;
		map.put("search_username", search_username);
		
		int totalCount = manageDAO.totalCount(map);
		int totalPages = totalCount%5>0?(totalCount/5)+1:(totalCount/5);
		
		if (direction == null || direction.equals("")) {
		    direction = "asc";
		}
		
		if(sort == null) {
			sort = "";
		}
		
		map.put("offset", offset);
		map.put("sort", sort);
		map.put("direction", direction);
		
		logger.info("search_username : "+search_username);
		logger.info("offset : "+offset);
		logger.info("sort : "+sort);
		logger.info("direction : "+direction);
		
		ArrayList<ManageDTO> list = manageDAO.userList(map);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("total", totalPages);
		result.put("list", list);
		
		logger.info("total : "+totalPages);
		
		return result;
	}

	public int userRole(HashMap<String, Object> param) {
		
		return manageDAO.userRole(param);
	}

	public int userState(HashMap<String, Object> param) {
		
		return manageDAO.userState(param);
	}

	public HashMap<String, Object> userInfo(int user_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<ManageDTO> info = manageDAO.userInfo(user_num);
		ArrayList<ManageDTO> post = manageDAO.userPost(user_num);
		ArrayList<ManageDTO> comment = manageDAO.userComment(user_num);
		ArrayList<ManageDTO> report = manageDAO.userReport(user_num);
		
		map.put("info", info);
		map.put("post", post);
		map.put("comment", comment);
		map.put("report", report);
		
		return map;
	}

	public HashMap<String, Object> userLog(int user_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<ManageDTO> log = manageDAO.userLog(user_num);
		
		map.put("log", log);
		
		return map;
	}

	public HashMap<String, Object> userBoard(int user_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<ManageDTO> post = manageDAO.userPost(user_num);
		ArrayList<ManageDTO> comment = manageDAO.userComment(user_num);
		
		map.put("post", post);
		map.put("comment", comment);
		
		return map;
	}

	public HashMap<String, Object> boardList(int page, String search_word, String sort, String direction, String state) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int offset = (page-1)*5;
		param.put("page", page);
		param.put("search_word", search_word);
		param.put("sort", sort);
		param.put("direction", direction);
		param.put("state", state);
		
		int totalCount = manageDAO.boardTotalCount(param);
		
		int totalPages = totalCount%5>0?(totalCount/5)+1:(totalCount/5);
		
		if (direction == null || direction.equals("")) {
		    direction = "asc";
		}
		
		if(sort == null) {
			sort = "";
		}
		
		param.put("offset", offset);
		
		ArrayList<ManageDTO> list = manageDAO.boardList(param);
		
		result.put("total", totalPages);
		result.put("list", list);
		
		return result;
	}

	public HashMap<String, Object> commentList(int page, String search_word, String sort, String direction, String state) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int offset = (page-1)*10;
		param.put("page", page);
		param.put("search_word", search_word);
		param.put("sort", sort);
		param.put("direction", direction);
		param.put("state", state);
		
		int totalCount = manageDAO.commentTotalCount(param);
		
		int totalPages = totalCount%10>0?(totalCount/10)+1:(totalCount/10);
		
		if (direction == null || direction.equals("")) {
		    direction = "asc";
		}
		
		if(sort == null) {
			sort = "";
		}
		
		param.put("offset", offset);
		
		ArrayList<ManageDTO> list = manageDAO.commentList(param);
		logger.info("totalPages : "+totalPages);
		result.put("total", totalPages);
		result.put("list", list);
		
		return result;
	}

	public HashMap<String, Object> reportInfo(int post_num) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result = manageDAO.reportInfo(post_num);
		
		return result;
	}

	public int postBlind(int post_num) {
		
		return manageDAO.postBlind(post_num);
	}

	public int commentBlind(int comment_num) {
		return manageDAO.commentBlind(comment_num);
	}

	public int commentClear(int comment_num) {
		return manageDAO.commentClear(comment_num);
	}

	public int postClear(int post_num) {
		return manageDAO.postClear(post_num);
	}

	public int reportWrite(HashMap<String, Object> param) {
		return manageDAO.reportWrite(param);
	}

	public int reportPostCheck(int post_num) {
		return manageDAO.reportPostCheck(post_num);
	}

}
