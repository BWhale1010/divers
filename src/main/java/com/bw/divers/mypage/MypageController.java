package com.bw.divers.mypage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bw.divers.user.UserDTO;
import com.bw.divers.user.UserService;

@Controller
public class MypageController {
	@Autowired MypageService mypageService;
	@Autowired UserService userService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		logger.info("마이페이지");
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserDTO userDTO = userService.getUserbyUsername(username);
    	String new_filename = userDTO.getNew_filename();
    	logger.info("new_filename : "+new_filename);
    	
    	model.addAttribute("user", userDTO);
		
		return "/mypage/mypage";
	}
	
	@PostMapping("/mypage/profile")
	public String profile(MultipartFile profileImg, HttpSession session) {
		logger.info("프로필 사진 업로드");

		int user_num = (int) session.getAttribute("user_num");
		mypageService.profile(profileImg, user_num);

		
		return "redirect:/mypage";
	}
	


}
