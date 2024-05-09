package com.bw.divers.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageApiController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ManageService manageService;

}
