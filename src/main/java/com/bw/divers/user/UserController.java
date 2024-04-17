package com.bw.divers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired UserService userService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("login")
	public String login() {
		
		return "/user/login";
	}
	
	@GetMapping("joinCheck")
	public String joinCheck() {
		
		return "/user/joinCheck";
	}
	
	@GetMapping("join")
	public String join() {
		
		return "/user/join";
	}
}
