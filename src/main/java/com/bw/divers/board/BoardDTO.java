package com.bw.divers.board;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("board")
public class BoardDTO {
	private int post_num;
	private int user_num;
	private int small_category_num;
	private int category;
	private Date board_date;
	private String title;
	private String content;
	private int count;
	private int post_blind;
	private String nickname;
	
	private String small_class_name;
	private int big_category_num;
	
	private String big_class_name;
	
	private int comment_num;
	private Date comment_date;
	private String comment;
	private Boolean comment_blind;
	
	private int report_num;
	private int sort_num;
	private String report_detail;
	
	private String sort_name;
	
	private int thumb_comment_num;
	private int thumb_comment_user;
	
	private int thumb_post_num;
	private int thumb_post_user_num;
	
	private int recommend;
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getSmall_category_num() {
		return small_category_num;
	}
	public void setSmall_category_num(int small_category_num) {
		this.small_category_num = small_category_num;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPost_blind() {
		return post_blind;
	}
	public void setPost_blind(int post_blind) {
		this.post_blind = post_blind;
	}
	public String getSmall_class_name() {
		return small_class_name;
	}
	public void setSmall_class_name(String small_class_name) {
		this.small_class_name = small_class_name;
	}
	public int getBig_category_num() {
		return big_category_num;
	}
	public void setBig_category_num(int big_category_num) {
		this.big_category_num = big_category_num;
	}
	public String getBig_class_name() {
		return big_class_name;
	}
	public void setBig_class_name(String big_class_name) {
		this.big_class_name = big_class_name;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getComment_blind() {
		return comment_blind;
	}
	public void setComment_blind(Boolean comment_blind) {
		this.comment_blind = comment_blind;
	}
	public int getReport_num() {
		return report_num;
	}
	public void setReport_num(int report_num) {
		this.report_num = report_num;
	}
	public int getSort_num() {
		return sort_num;
	}
	public void setSort_num(int sort_num) {
		this.sort_num = sort_num;
	}
	public String getReport_detail() {
		return report_detail;
	}
	public void setReport_detail(String report_detail) {
		this.report_detail = report_detail;
	}
	public String getSort_name() {
		return sort_name;
	}
	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}
	public int getThumb_comment_num() {
		return thumb_comment_num;
	}
	public void setThumb_comment_num(int thumb_comment_num) {
		this.thumb_comment_num = thumb_comment_num;
	}
	public int getThumb_comment_user() {
		return thumb_comment_user;
	}
	public void setThumb_comment_user(int thumb_comment_user) {
		this.thumb_comment_user = thumb_comment_user;
	}
	public int getThumb_post_num() {
		return thumb_post_num;
	}
	public void setThumb_post_num(int thumb_post_num) {
		this.thumb_post_num = thumb_post_num;
	}
	public int getThumb_post_user_num() {
		return thumb_post_user_num;
	}
	public void setThumb_post_user_num(int thumb_post_user_num) {
		this.thumb_post_user_num = thumb_post_user_num;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
	
	
	
	
	

}
