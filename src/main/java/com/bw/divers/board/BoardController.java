package com.bw.divers.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	@Autowired
	BoardService boardService;

	@GetMapping("/board/list/{boardId}")
	public String boardList(@PathVariable int boardId, Model model) {

		model.addAttribute("small_category_num", boardId);
		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite(@RequestParam int boardId, Model model, HttpServletRequest request) {

		logger.info("boardId : " + boardId);

		ArrayList<BoardDTO> categoryNum = boardService.categoryLIst();

		model.addAttribute("boardId", boardId);
		request.setAttribute("categoryNum", categoryNum);

		return "board/write";
	}

	@GetMapping("/board/detail/{postNum}")
	public String boardDetail(@PathVariable int postNum, Model model, HttpSession session) {
		logger.info("게시판 보기 postNum : " + postNum);
		Integer user_numSession = (Integer) session.getAttribute("user_num");
		int user_num = 0;
		if (user_numSession != null) {
			user_num = user_numSession.intValue();
		} else {
			user_num = 0;
		}
		logger.info("user_num : " + user_num);
		BoardDTO post = boardService.postDetail(postNum, user_num);
		int reportCount = boardService.reportCount(postNum);

		model.addAttribute("post", post);
		model.addAttribute("reportCount", reportCount);

		return "/board/post";
	}

	@GetMapping("/board/edit/{postNum}")
	public String boardEdit(@PathVariable int postNum, Model model, HttpSession session) {
		logger.info("게시판 수정 postNum : " + postNum);

		int user_num = (int) session.getAttribute("user_num");

		BoardDTO post = boardService.postDetail(postNum, user_num);
		ArrayList<BoardDTO> categoryNum = boardService.categoryLIst();

		model.addAttribute("post", post);
		model.addAttribute("categoryNum", categoryNum);

		return "/board/update";
	}

}
