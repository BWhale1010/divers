<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<main id="main">

	<section id="contact" class="contact mb-5">
	<c:set var="categoryList" value="${categoryNum }"></c:set>
	<c:set var="boardId" value="${boardId }"></c:set>
		<div class="container">
			<div class="row">
				<div class="col-md-9 post-content" data-aos="fade-up">
					<div class="form mt-5">
						<form action="/join" method="post" role="form" class="common-form">

							<div>
								<label for="title" style="margin-bottom: 10px">제목</label>
								<div class="form-group">
									<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요." required> 
									<span class="msg1"></span>
								</div>
							</div>


						<div class="row">
							<label for="category" style="margin-bottom: 10px">카테고리</label>
							<div class="form-group d-flex flex-column">

								<div class="align-horizontal">

									<c:choose>
									<c:when test="${sessionScope.roleNum eq 3 }">
										<select id="category" class="form-control">
											<option value="init">선택해주세요</option>
											<c:forEach var="categoryNum" items="${categoryNum}" varStatus="loop">
												<c:if test="${not empty categoryNum and loop.index != 2}">
													<option value="${categoryNum.small_category_num}" 
														<c:if test="${categoryNum.small_category_num eq boardId}">selected="selected"</c:if>>
													${categoryNum.small_class_name}</option>
												</c:if>
											</c:forEach>
										</select>
										</c:when>
										
										<c:otherwise>
											<select id="category" class="form-control">
												<option value="init">선택해주세요</option>
												<c:forEach var="categoryNum" items="${categoryNum}" >
													<option value="${categoryNum.small_category_num}" 
														<c:if test="${categoryNum.small_category_num eq boardId}">selected="selected"</c:if>>
													${categoryNum.small_class_name}</option>											
												</c:forEach>
											</select>										
										</c:otherwise>
										</c:choose>

									</div>

								<div class="d-flex justify-content-between">
									<div class="msg2"></div>
									<div id="customInput" class="align-horizontal"></div>
								</div>

							</div>
						</div>
							
							<div class="row">
								<label for="content" style="margin-bottom: 10px">내용</label>
								<div class="form-group ">									
									<textarea class="form-control  summernote" rows="15" ></textarea>
									<span class="msg3"></span>
								</div>
							</div>

							<br>
							<hr>
							<br>





							<div class="btn-group d-flex justify-content-center common-btn">
								<div class="text-center">
									<button type="button" onclick="location.href='/';">취소</button>
								</div>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="text-center">
									<button id="write-btn" type="button">저장</button>
								</div>
							</div>

						</form>
					</div>


				</div>
				<div class="col-md-3">

					<%@include file="./sidebar.jsp"%>

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
					</div>
					<!-- End Tags -->

				</div>
			</div>
		</div>
	</section>
</main>
<!-- End #main -->

<script>
$('.summernote').summernote({
	tabsize : 2,
	height : 300,
	toolbar: [
	    // [groupName, [list of button]]
	    ['fontname', ['fontname']],
	    ['fontsize', ['fontsize']],
	    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	    ['color', ['forecolor','color']],
	    ['table', ['table']],
	    ['para', ['ul', 'ol', 'paragraph']],
	    ['height', ['height']],
	    ['insert',['picture','link','video']],
	    ['view', ['fullscreen', 'help']]
	  ],
	fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
	fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
});

</script>

<%@include file="../layout/footer.jsp"%>