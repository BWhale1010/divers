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

}
