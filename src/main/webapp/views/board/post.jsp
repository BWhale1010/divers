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

    <section class="single-post-content">
      <div class="container">
        <div class="row">
          <div class="col-md-9 post-content" data-aos="fade-up">
			<div class="single-post">
              <div class="post-meta">
	              <span class="date">${post.small_class_name }</span> <span class="mx-1">•</span> <span>${post.board_date }</span>
	              | <span class="date">${post.nickname }</span> <span class="mx-1">•</span> <span>조회수 ${post.count }</span>
              </div>
              
              <h1 class="mb-5">${post.title }</h1> <!-- 제목  -->
              <p>${post.content }</p>
            </div>
            
            <br><br><hr>
            

  
<!-- 댓글 리스트  -->
		<div class="comments">
		
		<div class="card bg-custom">
		    <div class="row">
		        <div class="col">
		            <h4 class="comment-title py-4">2 댓글</h4>
		        </div>
		        <div class="col">
		            <div class="card-body text-end">
		                <a href="#"><img src="/assets/img/thumb.png" style="width: 6%"></a> ${post.recommend }
		                <span style="margin: 0 10px;">|</span>
		                
		                <c:choose>
		                	<c:when test="${sessionScope.user_num == post.user_num}">	                		
								<div class="dropdown">
								  <a class="dropbtn">• • •</a>
								  <div class="dropdown-content">
								    <a href="/board/edit/${post.post_num }">수정하기</a>
								    <a href="#">삭제하기</a>
								  </div>
								</div>
		                	</c:when>
		                	<c:otherwise>
		                		<a href="#">신고하기</a>    
		                	</c:otherwise>		                
		                </c:choose>		                
		                   
		            </div>
		        </div>
		    </div>
		</div>
              
              <div class="comment d-flex mb-4">
                <div class="flex-shrink-0">
                  <div class="avatar avatar-sm rounded-circle">
                    <img class="avatar-img" src="/assets/img/person-5.jpg" alt="">
                  </div>
                </div>
                <div class="flex-grow-1 ms-2 ms-sm-3">
                
                  <div class="comment-meta d-flex align-items-baseline">
                    <h6 class="me-2">Jordan Singer</h6>
                    <span class="text-muted">2d</span>
                  </div>
                  <div class="comment-body">
                    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Non minima ipsum at amet doloremque qui magni, placeat deserunt pariatur itaque laudantium impedit aliquam eligendi repellendus excepturi quibusdam nobis esse accusantium.
                  </div>
                  <div>
                  
    		        <div class="card-body text-end">
		                <a href="#"><img src="/assets/img/thumb.png" style="width: 3%"></a> 0
		                <span style="margin: 0 10px;">|</span> 
		                <a href="#">신고하기</a>       
		            </div>
                  
                  
                  </div>
                </div>
              </div>
              
              <div class="comment d-flex">
                <div class="flex-shrink-0">
                  <div class="avatar avatar-sm rounded-circle">
                    <img class="avatar-img" src="/assets/img/person-2.jpg" alt="">
                  </div>
                </div>
                <div class="flex-shrink-1 ms-2 ms-sm-3">
                  <div class="comment-meta d-flex">
                    <h6 class="me-2">Santiago Roberts</h6>
                    <span class="text-muted">4d</span>
                  </div>
                  <div class="comment-body">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Iusto laborum in corrupti dolorum, quas delectus nobis porro accusantium molestias sequi.
                  </div>
                </div>
              </div>
              
            </div>
            
            <br>
            
                      <!-- 댓글쓰기  -->
				<div class="col-12 mb-3">
                    <label for="comment-message">Message</label>
                    <textarea class="form-control" id="comment-message" placeholder="Enter your name" cols="30" rows="10" style="height: 60px;"></textarea>
                  </div>
                  
                  <div class="col-12">
                    <input type="submit" class="btn btn-primary" value="댓글 쓰기">
                  </div>
            
            
            
            
          </div>
          

           
          
          <div class="col-md-3">
  
  <%@include file="./sidebar.jsp" %>

<!--             <div class="aside-block"> -->
<!--               <h3 class="aside-title">Video</h3> -->
<!--               <div class="video-post"> -->
<!--                 <a href="https://www.youtube.com/watch?v=AiFfDjmd0jU" class="glightbox link-video"> -->
<!--                   <span class="bi-play-fill"></span> -->
<!--                   <img src="assets/img/post-landscape-5.jpg" alt="" class="img-fluid"> -->
<!--                 </a> -->
<!--               </div> -->
<!--             </div>End Video -->

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

            <div class="aside-block">
              <h3 class="aside-title">Tags</h3>
              <ul class="aside-tags list-unstyled">
                <li><a href="category.html">Business</a></li>
                <li><a href="category.html">Culture</a></li>
                <li><a href="category.html">Sport</a></li>
                <li><a href="category.html">Food</a></li>
                <li><a href="category.html">Politics</a></li>
                <li><a href="category.html">Celebrity</a></li>
                <li><a href="category.html">Startups</a></li>
                <li><a href="category.html">Travel</a></li>
              </ul>
            </div><!-- End Tags -->

          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->

  <%@include file="../layout/footer.jsp" %>