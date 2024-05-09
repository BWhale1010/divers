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

	public HashMap<String, Object> listAdd(int page, String search_username) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int offset =(page-1)*10;
		map.put("search_username", search_username);
		
		int totalCount = manageDAO.totalCount(map);
		int totalPages = totalCount%5>0?(totalCount/5)+1:(totalCount/5);
		map.put("offset", offset);
		
		ArrayList<ManageDTO> list = manageDAO.userList(map);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("total", totalPages);
		result.put("list", list);
		
		logger.info("total : "+totalPages);
		
		return result;
	}

}
