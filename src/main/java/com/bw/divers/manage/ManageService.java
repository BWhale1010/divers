package com.bw.divers.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@MapperScan("com.bw.divers.manage")
@Service
public class ManageService {
	Logger logger = LoggerFactory.getLogger(getClass());

}
