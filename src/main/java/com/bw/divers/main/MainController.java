package com.bw.divers.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bw.divers.user.UserDTO;
import com.bw.divers.user.UserService;

@Controller
public class MainController {
	@Autowired MainService mainService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserService userService;

	@GetMapping("/")
	public String main(Model model) {
		String msg = "로그인이 완료되었습니다.";
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		
		UserDTO userDTO = userService.getUserbyUsername(username);
		
		if(userDTO != null) {
			userDTO.setPassword(null);
			model.addAttribute("user", userDTO);
			model.addAttribute("msg", msg);
		}

		return "index";
	}
}
