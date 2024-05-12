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

}
