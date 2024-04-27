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

	public HashMap<String, Object> listAdd(int page, int category) {
		int offset = (page-1)*5;
		int totalCount = boardDAO.totalCount(category);
		int totalPages = totalCount%5>0?(totalCount/5)+1:(totalCount/5);
		
		logger.info("총 페이지 : "+totalPages);
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<BoardDTO> list = boardDAO.boardList(offset, category);
		logger.info("content : "+list.get(0).getContent());
		
		result.put("total", totalPages);
		result.put("list", list);
		
		return result;
	}

	public BoardDTO postDetail(int postNum) {
		logger.info("글 상세보기 서비스 : "+postNum);
		return boardDAO.postDetail(postNum);
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


}
