package com.bw.divers.mypage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bw.divers.user.UserDTO;
import com.bw.divers.user.UserService;

@Controller
public class MypageController {
	@Autowired MypageService mypageService;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired UserService userService;
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		logger.info("마이페이지");
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserDTO userDTO = userService.getUserbyUsername(username);
    	
    	model.addAttribute("user", userDTO);
		
		return "/mypage/mypage";
	}


}
