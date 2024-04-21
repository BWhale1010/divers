package com.bw.divers.user;

import java.util.HashMap;

public interface UserDAO {

	int usernameCheck(String username);

	int emailCheck(String email);

	int nicknameCheck(String nickname);

	int join(HashMap<String, String> params);

	String enc_pw(String username);

	UserDTO login(String username);

	UserDTO getUserbyUsername(String username);


}
