package com.bw.divers.manage;

import java.util.ArrayList;
import java.util.HashMap;

public interface ManageDAO {

	int totalCount(HashMap<String, Object> map);

	ArrayList<ManageDTO> userList(HashMap<String, Object> map);

	int userRole(HashMap<String, Object> param);

	int userState(HashMap<String, Object> param);

	ArrayList<ManageDTO> userInfo(int user_num);

	ArrayList<ManageDTO> userPost(int user_num);

	ArrayList<ManageDTO> userComment(int user_num);

	ArrayList<ManageDTO> userReport(int user_num);


}
