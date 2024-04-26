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






}
