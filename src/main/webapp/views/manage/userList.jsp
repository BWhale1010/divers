<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section>
		<input id="user_num" type="hidden" value=${sessionScope.user_num }>
		<div class="container">
			<div class="row">

				<div class="col-md-9" data-aos="fade-up">
					<div>
						<h3 class="category-title">유저 관리</h3>
						
					    <div>
					    	<span onclick="listAdd(1, '','','')">기본 순</span> |
					        <span onclick="sortUsers('join_date')">가입 순</span> <span id="join_dateSort">↑</span> |
					        <span onclick="sortUsers('reportCount')">신고 순</span> <span id="reportCountSort">↑</span> |
					        <span onclick="sortUsers('sspsCount')">정지 순</span> <span id="sspsCountSort">↑</span>
					    </div>
					</div>
					<div id="userList"></div>

					<div class="text-start py-4">

						<ul class="pagination" id="pagination"></ul>

					</div>


				</div>

				<div class="col-md-3">

					<%@include file="../board/sidebar.jsp"%>

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
					</div>
					<!-- End Categories -->

					<div class="aside-block">
						<h3 class="aside-title">검색</h3>
						<input id="search-word" type="text" placeholder="Search" class="form-control">
						<button id="search-btn" class="btn btn-dark" type="button" style="margin-top: 10px;">검색</button>

					</div>
					<!-- End Tags -->

				</div>
</div>
			</div>

	</section>
</main>
<!-- End #main -->




<script src="/assets/js/manage/userList.js"></script>

<%@include file="../layout/footer.jsp"%>