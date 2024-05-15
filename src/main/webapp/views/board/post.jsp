<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<style>
  .dropdown {
    position: relative;
    display: inline-block;
  }
  .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
  }
  .dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
  }
  .dropdown-content a:hover {
    background-color: #f1f1f1;
  }
  .dropdown:hover .dropdown-content {
    display: block;
  }
</style>

  <main id="main">

<input type="hidden" id="loginId" value="${sessionScope.user_num }">
<input type="hidden" id="post_num" value="${post.post_num }">
<input type="hidden" id="postUser" value="${post.user_num }">
<input type="hidden" id="small_category_num" value="${post.small_category_num }">
    <section class="single-post-content">
      <div class="container">
        <div class="row">
          <div class="col-md-9 post-content" data-aos="fade-up">
			<div class="single-post">
              <div class="post-meta">
	              <span class="date">${post.small_class_name }</span> <span class="mx-1">•</span> <span>${post.board_date }</span>
	              | <span class="date">${post.nickname }</span> <span class="mx-1">•</span> <span>조회수 ${post.count }</span>
	              <c:if test="${sessionScope.role_num == 1 || sessionScope.role_num == 2}">
	              	<c:if test="${post.post_state_num == 1}">
	              		| <span>글 상태 : 정상</span>
	              	</c:if>
	              	<c:if test="${post.post_state_num == 2}">
	              		| <span>글 상태 : 블라인드</span>
	              	</c:if>
	              	<c:if test="${post.post_state_num == 3}">
	              		| <span>글 상태 : 삭제</span>
	              	</c:if>
	              	
	              </c:if>
	              
              </div>
              
              <h1 class="mb-5">${post.title }</h1> <!-- 제목  -->
              <p>${post.content }</p>
             
            </div>
            
            <br>
            <c:if test="${sessionScope.role_num ==1 || sessionScope.role_num == 2}">
	           <c:if test="${post.report_detail != null }">
	            	<div class="common-btn">
	            		<button data-toggle="modal" data-target="#reportModal" data-post_num="${post.post_num}">신고내용</button>
	            	</div>		
            	</c:if>
            </c:if>
            <br><hr>
            

  
<!-- 댓글 리스트  -->
		<div class="comments">
		
		<div class="card bg-custom">
		    <div class="row">
		        <div class="col">

		        	<c:choose>
			        	<c:when test="${empty post.commentCount }">
			        		 <h4 class="comment-title py-4">댓글 0</h4>
			        	</c:when>
			        	<c:otherwise>
			        	  <h4 class="comment-title py-4">댓글 ${post.commentCount }</h4>
			        	</c:otherwise>	        	
		        	</c:choose>

		        </div>
		        <div class="col">
		            <div class="card-body text-end">
		            <c:choose>
		            	<c:when test="${post.is_recommended == 0 }">
			            	<a onclick="postRecommend()"><img src="/assets/img/thumb.png" style="width: 6%"></a> ${post.recommend }
			                <span style="margin: 0 10px;">|</span>
		            	</c:when>
		            	<c:otherwise>
		            		<a onclick="postRecommend()"><img src="/assets/img/thumb_color.png" style="width: 6%"></a> ${post.recommend }
			                <span style="margin: 0 10px;">|</span>
		            	</c:otherwise>
		            
		            </c:choose>

		                
		                <c:choose>
		                	<c:when test="${sessionScope.user_num == post.user_num}">	                		
								<div class="dropdown">
								  <a class="dropbtn">• • •</a>
								  <div class="dropdown-content">
								    <a href="/board/edit/${post.post_num }">수정하기</a>
								    <a onclick="/deletePost(${post.post_num}, ${post.small_category_num },${post.user_num })">삭제하기</a>
								  </div>
								</div>
		                	</c:when>
		                	<c:otherwise>
		                		<c:if test= "${sessionScope.role_num == 3 || sessionScope.role_num == null }">
		                			<a onclick="reportWriteModal(${post.post_num})" >신고하기</a>
		                		</c:if>
		                		<c:if test="${sessionScope.role_num == 1 || sessionScope.role_num == 2}">
		                			<c:if test="${post.post_state_num == 1 }">
		                				<a onclick = "postBlind(${post.post_num});">블라인드</a>
		                			</c:if>
		                			<c:if test="${post.post_state_num == 2 }">
		                				<a onclick = "postClear(${post.post_num});">블라인드 해제</a>
		                			</c:if>
		                		</c:if>
		                		    
		                	</c:otherwise>		                
		                </c:choose>		                
		                   
		            </div>
		        </div>
		    </div>
		</div>
              
              <div id="commentList">             
              </div>
              
              <ul class="pagination" id="pagination"></ul>

              </div>
              
                          <br>
            
                      <!-- 댓글쓰기  -->
				<div class="col-12 mb-3">
                    <label for="comment-message">comment</label>
                    <textarea class="form-control" id="comment" placeholder="댓글을 작성해 주세요." cols="30" rows="10" style="height: 60px;"></textarea>
                  </div>
                  
                  <div class="col-12">
                  	<input id="cancel-btn" type="button" class="btn btn-primary" value="취소" style="display: none;">
                  	<input id="commentEdit-btn" type="button" class="btn btn-primary" value="댓글 수정" style="display: none;">
                    <input id="comment-btn" type="button" class="btn btn-primary" value="댓글 쓰기">
                  </div>
                  
              
            </div>
            
            
                      <div class="col-md-3">
  
  <%@include file="./sidebar.jsp" %>


            <div class="aside-block">
              <h3 class="aside-title">Categories</h3>
              <ul class="aside-links list-unstyled">
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Business</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Culture</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Sport</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Food</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Politics</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Celebrity</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Startups</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Travel</a></li>
              </ul>
            </div><!-- End Categories -->



          </div>

            
            
            
            
          </div>
          

           
          

        </div>
<!--       </div> -->
    </section>
  </main><!-- End #main -->

<script src="/assets/js/post.js"></script>
<%@ include file="./reportWriteModal.jsp" %>
<%@ include file="../manage/reportModal.jsp" %>
  <%@include file="../layout/footer.jsp" %>
