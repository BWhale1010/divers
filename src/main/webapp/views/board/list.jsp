<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section>
		<input id="small_category_num" type="hidden" value=${small_category_num }> 
		<input id="user_num" type="hidden" value=${sessionScope.user_num }>
		<input id="role_num" type="hidden" value=${sessionScope.role_num }>
		<div class="container">
			<div class="row">

				<div class="col-md-9" data-aos="fade-up">
					<c:if test="${small_category_num == 1}">
						<h3 class="category-title">Category: 유머</h3>
					</c:if>
					<c:if test="${small_category_num == 2}">
						<h3 class="category-title">Category: 정보</h3>
					</c:if>
					<c:if test="${small_category_num == 3}">
						<h3 class="category-title">Category: 공지</h3>
					</c:if>
					<c:if test="${small_category_num == 4}">
						<h3 class="category-title">Category: 국내 축구</h3>
					</c:if>
					<c:if test="${small_category_num == 5}">
						<h3 class="category-title">Category: 해외 축구</h3>
					</c:if>
					<c:if test="${small_category_num == 6}">
						<h3 class="category-title">Category: 국내 야구</h3>
					</c:if>
					<c:if test="${small_category_num == 7}">
						<h3 class="category-title">Category: 해외 야구</h3>
					</c:if>
					<c:if test="${small_category_num == 8}">
						<h3 class="category-title">Category: E-SPORTS</h3>
					</c:if>
					<c:if test="${small_category_num == 9}">
						<h3 class="category-title">Category: 국내 음악</h3>
					</c:if>
					<c:if test="${small_category_num == 10}">
						<h3 class="category-title">Category: 해외 음악</h3>
					</c:if>
					<c:if test="${small_category_num ==11}">
						<h3 class="category-title">Category: 동양 미술</h3>
					</c:if>
					<c:if test="${small_category_num == 12}">
						<h3 class="category-title">Category: 서양 미술</h3>
					</c:if>
					<c:if test="${small_category_num == 13}">
						<h3 class="category-title">Category: 영화</h3>
					</c:if>



					<div id="boardList"></div>

					<div class="text-start py-4">
							
						<ul class="pagination" id="pagination"></ul>


						<div class="writeBtn" style="display: inline-block; float: right;">
							<button type="button" onclick="postWrite();">글쓰기</button>
						</div>
					</div>


				</div>

				<div class="col-md-3">

					<%@include file="./sidebar.jsp"%>

					<div class="aside-block">
						<h3 class="aside-title">Categories</h3>
							<%@include file="./categories.jsp"%>
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




<script src="/assets/js/list.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script> -->
<%@include file="../layout/footer.jsp"%>