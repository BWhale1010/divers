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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
//	@PostMapping("/user/login")
//	public String loginProc(@RequestParam String username, String password, RedirectAttributes rttr, Model model) {
//		logger.info("로그인 컨트롤러");
//
//		String page = "";
//		String msg = "";
//		
//		UserDTO loginId = userService.login(username, password);
//		
//		String loginUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDTO loginInfo = userService.getUserbyUsername(loginUsername);
//		logger.info("userDTO : "+loginInfo.getRole_name());
////		userDTO.setPassword(null);
//			if(loginId == null) {
//				page = "redirect:/user/login";
//				msg = "아이디와 비밀번호를 확인해주세요.";
//			}else {
//				page = "redirect:/";
//				msg = "로그인이 완료되었습니다.";
//			}
//
//		rttr.addFlashAttribute("msg", msg);
//		rttr.addFlashAttribute("loginInfo",loginInfo);
//
//		
//		return page;
//	}
	

}
