package com.bw.divers.board;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.IOUtils;

@Service
@MapperScan("com.bw.divers.board")
public class BoardService {

	@Autowired BoardDAO boardDAO;
	Logger logger = LoggerFactory.getLogger(getClass());	
	
	public ArrayList<BoardDTO> categoryLIst() {
		
		 
		return boardDAO.categoryList();
	}

	public int boardWrite(HashMap<String, Object> params) {
		logger.info("게시글 저장 서비스");
		int row = boardDAO.boardWrite(params);
		return row;
	}

	public HashMap<String, Object> listAdd(int page, int category, String search_word) {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		int offset = (page-1)*5;
		map1.put("category", category);
		map1. put("search_word", search_word);
		
		int totalCount = boardDAO.totalCount(map1);
		logger.info("totalCount : "+totalCount);
		
		int totalPages = totalCount%5>0?(totalCount/5)+1:(totalCount/5);
		map1. put("offset", offset);

		logger.info("총 페이지 : "+totalPages);

		logger.info("category : "+map1.get("category"));
		logger.info("search_word : "+map1.get("search_word"));
		logger.info("offset : "+map1.get("offset"));
		
		ArrayList<BoardDTO> list = boardDAO.boardList(map1);
		logger.info("list 개수 : "+list.size());
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("total", totalPages);
		result.put("list", list);
		
		return result;
	}

	public BoardDTO postDetail(int postNum, int user_num) {
		logger.info("글 상세보기 서비스 : "+postNum);
		boardDAO.postCount(postNum);
		return boardDAO.postDetail(postNum, user_num);
	}

	public String thumbnail(int postNum) {
	    logger.info("썸네일 서비스 : " + postNum);
	    String thumbnailBase64 = "";
	    try {
	        String content = boardDAO.thumbnail(postNum);
	            Document doc = Jsoup.parse(content);
	            Elements imgTags = doc.select("img");

	            if (imgTags.isEmpty()) {	               
	                thumbnailBase64 = "basic";
	            } else {
	                Element firstImgTag = imgTags.first();
	                String imgUrl = firstImgTag.attr("src");
	                String base64Data = imgUrl.split(",")[1];
	                byte[] imageBytes = Base64.getDecoder().decode(base64Data);
	                BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
	                BufferedImage thumbnail = Thumbnails.of(originalImage).size(415, 263).asBufferedImage();
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                ImageIO.write(thumbnail, "jpg", baos);
	                byte[] thumbnailBytes = baos.toByteArray();
	                thumbnailBase64 = java.util.Base64.getEncoder().encodeToString(thumbnailBytes);
	                logger.info("thumbnailBase64 : " + thumbnailBase64);
	                baos.close();
	            }
	  
	    } catch (IOException e) {
	        logger.error("IOException occurred: " + e.getMessage());
	    }
	    return thumbnailBase64;
	}

	public String text(int postNum) {
		String text = boardDAO.thumbnail(postNum);
		Document doc = Jsoup.parse(text);
		
		Elements imgElements = doc.select("img");
		
		imgElements.remove();
		
		String textOnly = doc.text();
		
		
		return textOnly;
	}

	public int boardUpdate(String postNum, HashMap<String, String> param) {
		logger.info("게시글 수정 서비스 : "+postNum);
		
		param.put("postNum", postNum);
		
		int success = boardDAO.boardUpdate(param);
		
		return success;
	}

	public int boardDelete(int postNum) {
		logger.info("게시글 삭제 서비스 : "+postNum);
		return boardDAO.boardDelete(postNum);
	}

	public int commentWrite(HashMap<String, Object> params) {
		logger.info("댓글 작성 서비스");
		return boardDAO.commentWrite(params);
	}

	public HashMap<String, Object> commentList(int page, int post_num, HttpSession session) {
		logger.info("댓글 리스트 서비스 post_num : "+post_num);
		
		int offset = (page-1)*10;
		int totalCount = boardDAO.commentTotalCount(post_num);
		logger.info("totalCoutn : "+totalCount);
		int totalPages = totalCount%10>0?(totalCount/10)+1:(totalCount/10);
		
		Integer login_userNum = (Integer) session.getAttribute("user_num");
		Integer role_num = (Integer) session.getAttribute("role_num");
		int user_num = 0;
		
		if(login_userNum != null) {
			user_num = login_userNum.intValue();
		}else {
			user_num = 0;
		}
		if(role_num == null) {
			role_num = 3;
		}
		
		logger.info("총 페이지 : "+totalPages);
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.commentList(offset, post_num,user_num);
		result.put("total", totalPages);
		result.put("list", list);
		result.put("role_num", role_num);
		
		return result;
	}

	public int commentDelete(int comment_num) {
		logger.info("댓글 삭제 서비스");
		return boardDAO.commentDelete(comment_num);
	}

	public int commentEdit(int comment_num, String comment) {
		logger.info("댓글 수정 서비스");
		return boardDAO.commentEdit(comment_num, comment);
	}

	public int postThumb(int postNum, int user_num) {
		logger.info("게시글 추천 서비스");
		
		int postThumbCheck = boardDAO.postThumbCheck(postNum, user_num);
		logger.info("postThumbCheck : "+ postThumbCheck);
		
		if(postThumbCheck >0) {
			int postThumbMinus = boardDAO.postThumbMinus(postNum, user_num);
			return postThumbMinus;
		}else {
			int postThumbPlus = boardDAO.postThumbPlus(postNum, user_num);
			return postThumbPlus;
		}
		
	}

	public int commentThumb(int comment_num, int user_num) {
		logger.info("댓글 추천 서비스");
		
		int commentThumbCheck = boardDAO.commentThumbCheck(comment_num, user_num);
		
		if(commentThumbCheck >0) {
			int commentThumbMinus = boardDAO.commentThumbMinus(comment_num, user_num);
			return commentThumbMinus;
		}else {
			int commentThumbPlus = boardDAO.commentThumbPlus(comment_num, user_num);
			return commentThumbPlus;
		}
		
	}

	public int reportCount(int postNum) {
		return boardDAO.reportCount(postNum);
	}

	public HashMap<String, Object> recommendList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.recommendList(); 
		
		map.put("list", list);
		
		return map;
	}

	public HashMap<String, Object> newList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.newList(); 
		
		map.put("list", list);
		
		return map;
	}

	public HashMap<String, Object> popList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.popList(); 
		
		map.put("list", list);
		
		return map;
	}


}
