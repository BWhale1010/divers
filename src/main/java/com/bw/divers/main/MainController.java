package com.bw.divers.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bw.divers.user.UserService;

@Controller
public class MainController {
	@Autowired MainService mainService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserService userService;

	@GetMapping("/")
	public String main(Model model, @RequestParam(value="msg", required = false) String msg) {
		logger.info("홈 화면 호출");
		logger.info("msg : "+msg);
		if(msg != null) {
			model.addAttribute("msg", msg);
		}
		

		return "index";
	}
}
