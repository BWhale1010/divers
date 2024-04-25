package com.bw.divers.board;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@GetMapping("/board/list/{boardId}")
	public String boardList(@PathVariable int boardId, Model model) {
		
		logger.info("boardId"+boardId);
		
		model.addAttribute("boardId", boardId);

		
		return "board/list";
	}
	
	@GetMapping("/board/write")
	public String boardWrite(@RequestParam int boardId, Model model, HttpServletRequest request) {
		
		logger.info("boardId : "+boardId);

		ArrayList<BoardDTO> categoryNum = boardService.categoryLIst();
		
		model.addAttribute("boardId", boardId);
		request.setAttribute("categoryNum", categoryNum);

		
		return "board/write";
	}

}