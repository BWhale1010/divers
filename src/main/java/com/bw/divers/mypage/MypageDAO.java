package com.bw.divers.mypage;

public interface MypageDAO {

	int mypageUpdate(String username, String nickname);

	String oriPassword(String user_num);

	int imgCheck(int user_num);

	int delProfile(int user_num);

	void addProfile(int user_num, String oriFileName, String newFileName);

	int getUser_num(String username);

	int withDraw(int user_num);

}
