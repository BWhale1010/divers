package com.bw.divers.board;

import java.util.ArrayList;
import java.util.HashMap;

public interface BoardDAO {

	ArrayList<BoardDTO> categoryList();

	int boardWrite(HashMap<String, Object> params);

	int totalCount(int category);

	ArrayList<BoardDTO> boardList(int offset, int category);

	BoardDTO postDetail(int postNum, int user_num);

	String thumbnail(int postNum);

	int boardUpdate(HashMap<String, String> param);

	int boardDelete(int postNum);

	int commentWrite(HashMap<String, Object> params);

	int commentTotalCount(int post_num);

	ArrayList<BoardDTO> commentList(int offset, int post_num, int user_num);

	int commentDelete(int comment_num);

	int commentEdit(int comment_num, String comment);

	int postThumbCheck(int postNum, int user_num);

	int postThumbPlus(int postNum, int user_num);

	int postThumbMinus(int postNum, int user_num);

	int commentThumbCheck(int comment_num, int user_num);

	int commentThumbPlus(int comment_num, int user_num);

	int commentThumbMinus(int comment_num, int user_num);

	






}
