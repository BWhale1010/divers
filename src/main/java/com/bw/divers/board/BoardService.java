package com.bw.divers.board;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
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
import org.springframework.stereotype.Service;

import net.coobird.thumbnailator.Thumbnails;

@Service
@MapperScan("com.bw.divers.board")
public class BoardService {

	@Autowired
	BoardDAO boardDAO;
	Logger logger = LoggerFactory.getLogger(getClass());

	public ArrayList<BoardDTO> categoryLIst() {
		logger.info("게시판 카테고리 리스트 서비스");

		return boardDAO.categoryList();
	}

	public int boardWrite(HashMap<String, Object> params) {
		logger.info("게시글 저장 서비스");
		boardDAO.boardWrite(params);
		Object postNumObject = params.get("post_num");
		int post_num = 0;

		if (postNumObject instanceof BigInteger) {
			post_num = ((BigInteger) postNumObject).intValue();
		}

		return post_num;
	}

	public HashMap<String, Object> listAdd(int page, int category, String search_word) {
		logger.info("게시글 리스트 조회 서비스");
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		int offset = (page - 1) * 5;
		map1.put("category", category);
		map1.put("search_word", search_word);

		int totalCount = boardDAO.totalCount(map1);

		int totalPages = totalCount % 5 > 0 ? (totalCount / 5) + 1 : (totalCount / 5);
		map1.put("offset", offset);

		ArrayList<BoardDTO> list = boardDAO.boardList(map1);
		logger.info("list 개수 : " + list.size());
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("total", totalPages);
		result.put("list", list);

		return result;
	}

	public BoardDTO postDetail(int postNum, int user_num) {
		logger.info("글 상세보기 서비스 : " + postNum);
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
				if (imgUrl.startsWith("data:image")) {
					String[] parts = imgUrl.split(",");
					String mimeType = parts[0].split(":")[1].split(";")[0]; 
					String base64Data = parts[1];
					byte[] imageBytes = Base64.getDecoder().decode(base64Data);
					try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
							ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
						BufferedImage originalImage = ImageIO.read(bais);
						BufferedImage thumbnail = Thumbnails.of(originalImage).size(415, 263).asBufferedImage();
						String formatName = mimeType.split("/")[1];
						ImageIO.write(thumbnail, formatName, baos);
						byte[] thumbnailBytes = baos.toByteArray();
						thumbnailBase64 = Base64.getEncoder().encodeToString(thumbnailBytes);
						logger.info("thumbnailBase64 : " + thumbnailBase64);
					}
				} else {
					logger.info("이미지 URL이 Base64 형식이 아닙니다: " + imgUrl);
					thumbnailBase64 = "basic";
				}
			}
		} catch (IOException e) {
			logger.info("IOException 발생: " + e.getMessage(), e);
		} catch (Exception e) {
			logger.info("예상치 못한 오류 발생: " + e.getMessage(), e);
		}
		return thumbnailBase64;
	}

	public String text(int postNum) {
		logger.info("게시글 글 유무 확인 서비스");
		String text = boardDAO.thumbnail(postNum);
		Document doc = Jsoup.parse(text);

		Elements imgElements = doc.select("img");

		imgElements.remove();

		String textOnly = doc.text();

		return textOnly;
	}

	public int boardUpdate(String postNum, HashMap<String, String> param) {
		logger.info("게시글 수정 서비스 : " + postNum);

		param.put("postNum", postNum);

		int success = boardDAO.boardUpdate(param);

		return success;
	}

	public int boardDelete(int postNum) {
		logger.info("게시글 삭제 서비스 : " + postNum);
		return boardDAO.boardDelete(postNum);
	}

	public int commentWrite(HashMap<String, Object> params) {
		logger.info("댓글 작성 서비스");
		return boardDAO.commentWrite(params);
	}

	public HashMap<String, Object> commentList(int page, int post_num, HttpSession session) {
		logger.info("댓글 리스트 서비스 post_num : " + post_num);

		int offset = (page - 1) * 10;
		int totalCount = boardDAO.commentTotalCount(post_num);
		int totalPages = totalCount % 10 > 0 ? (totalCount / 10) + 1 : (totalCount / 10);

		Integer login_userNum = (Integer) session.getAttribute("user_num");
		Integer role_num = (Integer) session.getAttribute("role_num");
		int user_num = 0;

		if (login_userNum != null) {
			user_num = login_userNum.intValue();
		} else {
			user_num = 0;
		}
		if (role_num == null) {
			role_num = 3;
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.commentList(offset, post_num, user_num);
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
		logger.info("postThumbCheck : " + postThumbCheck);

		if (postThumbCheck > 0) {
			int postThumbMinus = boardDAO.postThumbMinus(postNum, user_num);
			return postThumbMinus;
		} else {
			int postThumbPlus = boardDAO.postThumbPlus(postNum, user_num);
			return postThumbPlus;
		}

	}

	public int commentThumb(int comment_num, int user_num) {
		logger.info("댓글 추천 서비스");

		int commentThumbCheck = boardDAO.commentThumbCheck(comment_num, user_num);

		if (commentThumbCheck > 0) {
			int commentThumbMinus = boardDAO.commentThumbMinus(comment_num, user_num);
			return commentThumbMinus;
		} else {
			int commentThumbPlus = boardDAO.commentThumbPlus(comment_num, user_num);
			return commentThumbPlus;
		}

	}

	public int reportCount(int postNum) {
		logger.info("게시글 신고 개수 확인 서비스");
		return boardDAO.reportCount(postNum);
	}

	public HashMap<String, Object> recommendList() {
		logger.info("메인 추천 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.recommendList();

		map.put("list", list);

		return map;
	}

	public HashMap<String, Object> newList() {
		logger.info("메인 최신 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.newList();

		map.put("list", list);

		return map;
	}

	public HashMap<String, Object> popList() {
		logger.info("메인 인기 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.popList();

		map.put("list", list);

		return map;
	}

	public HashMap<String, Object> sideRecommend() {
		logger.info("사이드 바 추천 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		ArrayList<BoardDTO> list = boardDAO.sideRecommend();

		map.put("list", list);

		return map;
	}

	public HashMap<String, Object> sidePop() {
		logger.info("사이드 바 인기 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		ArrayList<BoardDTO> list = boardDAO.sidePop();

		map.put("list", list);

		return map;
	}

	public HashMap<String, Object> sideNew() {
		logger.info("사이드 최신 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		ArrayList<BoardDTO> list = boardDAO.sideNew();

		map.put("list", list);

		return map;
	}

}
