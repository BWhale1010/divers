<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


  <main id="main">
    <section>
    <input id="boardId" type="hidden" value= ${boardId }>
    <input id="user_num" type="hidden" value=${sessionScope.user_num }>
      <div class="container">
        <div class="row">

          <div class="col-md-9" data-aos="fade-up">
            <h3 class="category-title">Category: Business</h3>
            
            <div id="boardList">
            
            </div>
            
            <div class="text-start py-4">
<!--               <div class="custom-pagination pagination"  id="pagination" style="display: inline-block;"> -->
<!--                 <a href="#" class="prev">Prevous</a> -->
<!--                 <a href="#" class="active">1</a> -->
<!--                 <a href="#">2</a> -->
<!--                 <a href="#">3</a> -->
<!--                 <a href="#">4</a> -->
<!--                 <a href="#">5</a> -->
<!--                 <a href="#" class="next">Next</a> -->
<!--               </div> -->

				<ul class="pagination" id="pagination"></ul>
              
              
	             <div class="writeBtn" style="display: inline-block; float: right;">
	            	<button type="button" onclick="location.href='/board/write?boardId='+boardId">글쓰기</button>
	            </div>
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




<script src="/assets/js/list.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script> -->
<%@include file="../layout/footer.jsp"%>