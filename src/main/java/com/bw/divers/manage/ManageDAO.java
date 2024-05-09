package com.bw.divers.manage;

import java.util.ArrayList;
import java.util.HashMap;

public interface ManageDAO {

	int totalCount(HashMap<String, Object> map);

	ArrayList<ManageDTO> userList(HashMap<String, Object> map);

}
