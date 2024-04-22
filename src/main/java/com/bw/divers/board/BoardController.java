package com.bw.divers.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/board/list/{boardId}")
	public String boardList(@PathVariable int boardId, Model model) {
		
		logger.info("boardId"+boardId);
		
		model.addAttribute("boardId", boardId);

		
		return "board/category";
	}
	
	@GetMapping("/board/write")
	public String boardWrite(@RequestParam int boardId, Model model) {
		
		logger.info("boardId"+boardId);
		
		model.addAttribute("boardId", boardId);

		
		return "board/write";
	}

}
