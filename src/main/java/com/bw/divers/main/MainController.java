package com.bw.divers.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired MainService mainService;
	Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	public String main() {
		
		return "main";
	}
}
