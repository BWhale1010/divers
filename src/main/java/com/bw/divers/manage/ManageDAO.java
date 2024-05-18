package com.bw.divers.manage;

import java.util.ArrayList;
import java.util.HashMap;

public interface ManageDAO {

	int totalCount(HashMap<String, Object> map);

	ArrayList<ManageDTO> userList(HashMap<String, Object> map);

	int userRole(HashMap<String, Object> param);

	int userState(int user_num, int state_num);

	ArrayList<ManageDTO> userInfo(int user_num);

	ArrayList<ManageDTO> userPost(int user_num);

	ArrayList<ManageDTO> userComment(int user_num);

	ArrayList<ManageDTO> userReport(int user_num);

	ArrayList<ManageDTO> userLog(int user_num);

	ArrayList<ManageDTO> boardList(HashMap<String, Object> param);

	int boardTotalCount(HashMap<String, Object> param);

	int commentTotalCount(HashMap<String, Object> param);

	ArrayList<ManageDTO> commentList(HashMap<String, Object> param);

	ArrayList<ManageDTO> reportInfo(int post_num);

	int postBlind(int post_num);

	int commentBlind(int comment_num);

	int commentClear(int comment_num);

	int postClear(int post_num);

	int reportWrite(HashMap<String, Object> param);

	int reportPostCheck(int post_num, int user_num);

	int reportCommentCheck(int comment_num, int user_num);

	int reportCommentWrite(HashMap<String, Object> param);

	int logWrite(int user_num, int sort_num, int alter_num);

	int findUser_num(String username);

	ArrayList<ManageDTO> commentInfo(int comment_num);






}
