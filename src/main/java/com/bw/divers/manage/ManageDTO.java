package com.bw.divers.manage;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("manage")
public class ManageDTO {
	
	private int user_num;
	private String username;
	private String email;
	private String nickname;
	private int platform_sort;
	private int state_num;
	private int role_num;

	private Date join_date;

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

	
	private int post_num;
	private int small_category_num;
	private int category;
	private Date board_date;
	private String title;
	private String content;
	private int count;
	
	private String small_class_name;
	private int big_category_num;
	
	private String big_class_name;
	
	private int comment_num;
	private Date comment_date;
	private String comment;
	
	private int report_num;
	private String report_detail;
	
	private int thumb_comment_num;
	private int thumb_comment_user;
	
	private int thumb_post_num;
	private int thumb_post_user_num;
	
	private int recommend;
	
	private int post_state_num;
	private String post_state_name;
	private int comment_state_num;
	private String comment_state_name;
	
	private int commentCount;
	private int is_recommended;
	private int postCount;

}
