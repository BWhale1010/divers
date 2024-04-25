package com.bw.divers.board;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MapperScan("com.bw.divers.board")
public class BoardService {

	@Autowired BoardDAO boardDAO;
	Logger logger = LoggerFactory.getLogger(getClass());	
	
	public ArrayList<BoardDTO> categoryLIst() {
		
		 
		return boardDAO.categoryList();
	}

	public int boardWrite(HashMap<String, Object> params) {
		logger.info("게시글 저장 서비스");
		int row = boardDAO.boardWrite(params);
		return row;
	}

	public HashMap<String, Object> listAdd(int page, int category) {
		int offset = (page-1)*5;
		int totalCount = boardDAO.totalCount(category);
		int totalPages = totalCount%5>0?(totalCount/5)+1:(totalCount/5);
		
		logger.info("총 페이지 : "+totalPages);
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.boardList(offset, category);
		logger.info("content : "+list.get(0).getContent());
		
		result.put("total", totalPages);
		result.put("list", list);
		
		return result;
	}

}
