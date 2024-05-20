package com.bw.divers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

	@Autowired UserService userService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/user/login")
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken) {
			return "/user/login";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/user/joinCheck")
	public String joinCheck() {
		
		return "user/joinCheck";
	}
	
	@GetMapping("/user/join")
	public String join() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken) {
			return "/user/join";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/user/idFind")
	public String idFind() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken) {
			return "/user/idFind";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/user/idFind")
	public String idFindResult(@RequestParam String email, Model model) {
		logger.info("아이디 찾기 email : "+email);
		String username = userService.idFind(email);
		
		model.addAttribute("username", username);
		model.addAttribute("email", email);
		
		return "/user/idFindResult";
	}
	
	@GetMapping("/user/pwReset")
	public String pwReset() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken) {
			return "/user/pwReset";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/user/pwResetResult")
	public String pwResetResult(@RequestParam String email, String username, Model model) {
		logger.info("비밀번호 재설정 이동 email, username : ",email, username);
		
		model.addAttribute("email", email);
		model.addAttribute("username", username);
		
		return "/user/pwResetResult";
	}
	





}
