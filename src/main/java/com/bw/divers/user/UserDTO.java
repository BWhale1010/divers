package com.bw.divers.user;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class UserDTO{
	private int user_num;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private int platform_sort;
	private int state_num;
	private int role_num;
	private Date join_date;
	
	private String role_name;
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPlatform_sort() {
		return platform_sort;
	}
	public void setPlatform_sort(int platform_sort) {
		this.platform_sort = platform_sort;
	}
	public int getState_num() {
		return state_num;
	}
	public void setState_num(int state_num) {
		this.state_num = state_num;
	}
	public int getRole_num() {
		return role_num;
	}
	public void setRole_num(int role_num) {
		this.role_num = role_num;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	
	

}
