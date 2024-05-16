package com.bw.divers.user;

import java.sql.Date;
import java.util.HashMap;

public interface UserDAO {

	int usernameCheck(String username);

	int emailCheck(String email);

	int nicknameCheck(String nickname);

	int join(HashMap<String, String> params);

	String enc_pw(String username);

	UserDTO login(String username);

	UserDTO getUserbyUsername(String username);

	String idFind(String email);

	String findEmail(String username);

	int pwResetFunc(String username, String enc_password);

	Date suspDate(int user_num);

	Date withDate(int user_num);



}
