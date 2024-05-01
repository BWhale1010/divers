package com.bw.divers.board;

import java.util.ArrayList;
import java.util.HashMap;

public interface BoardDAO {

	ArrayList<BoardDTO> categoryList();

	int boardWrite(HashMap<String, Object> params);

	int totalCount(int category);

	ArrayList<BoardDTO> boardList(int offset, int category);

	BoardDTO postDetail(int postNum);

	String thumbnail(int postNum);

	int boardUpdate(HashMap<String, String> param);

	int boardDelete(int postNum);

	int commentWrite(HashMap<String, Object> params);

	int commentTotalCount(int post_num);

	ArrayList<BoardDTO> commentList(int offset, int post_num);

	






}
