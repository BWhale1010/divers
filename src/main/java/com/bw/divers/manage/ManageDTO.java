package com.bw.divers.manage;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("manage")
@Data
public class ManageDTO {
	
	private int user_num;
	private String username;
	private String email;
	private String nickname;
	private int platform_sort;
	private int state_num;
	private int role_num;
	private Date join_data;
	
	private String role_name;
	private String platform_name;
	private String state_name;
	private String new_filename;
	private int profile_num;
	
	private int log_num;
	private Date alter_date;
	private int sort_num;
	private int alter_num;
	private String sort_name;
	private String alter_name;


}
