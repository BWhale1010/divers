package com.bw.divers.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MapperScan("com.bw.divers.user")
public class UserService {

	@Autowired UserDAO userDAO;
	Logger logger = LoggerFactory.getLogger(getClass());
	
}
