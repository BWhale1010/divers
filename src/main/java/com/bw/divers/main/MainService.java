package com.bw.divers.main;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MapperScan("com.bw.divers.main")
public class MainService {
	@Autowired MainDAO mainDAO;
	Logger logger = LoggerFactory.getLogger(getClass());

}
