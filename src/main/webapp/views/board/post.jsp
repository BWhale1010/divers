<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

  <main id="main">

    <section class="single-post-content">
      <div class="container">
        <div class="row">
          <div class="col-md-9 post-content" data-aos="fade-up">
			<div class="single-post">
              <div class="post-meta"><span class="date">${post.small_class_name }</span> <span class="mx-1">•</span> <span>${post.board_date }</span></div>
              
              <h1 class="mb-5">${post.title }</h1> <!-- 제목  -->
              
              <p>${post.content }</p>
          
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