<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>divers</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="/assets/img/favicon.png" rel="icon">
<link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" type="text/css">
<link href="/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" type="text/css">
<link href="/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" type="text/css">
<link href="/assets/vendor/aos/aos.css" rel="stylesheet" type="text/css">

<!-- Template Main CSS Files -->
<link href="/assets/css/variables.css" rel="stylesheet" type="text/css">
<link href="/assets/css/main.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
<script src="/assets/js/header.js"></script>

<!-- =======================================================
  * Template Name: ZenBlog
  * Template URL: https://bootstrapmade.com/zenblog-bootstrap-blog-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https:///bootstrapmade.com/license/
  ======================================================== -->
</head>



<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header d-flex align-items-center fixed-top">
		<div class="container-fluid container-xl d-flex align-items-center justify-content-between">

			<a href="/" class="logo d-flex align-items-center"> <!-- Uncomment the line below if you also wish to use an image logo --> <!-- <img src="assets/img/logo.png" alt=""> --> <img src="/assets/img/divers.png" style="width: 100px; height: auto;">
			</a>

			<nav id="navbar" class="navbar">
				<ul>
					<li class="dropdown"><a href="#"><span>자유게시판</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
						<ul>
							<li><a href="/board/list/1">유머</a></li>
							<li><a href="/board/list/2">정보</a></li>
							<li><a href="/board/list/3">공지</a></li>
						</ul></li>
					<li class="dropdown"><a href="#"><span>스포츠</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
						<ul>
							<li class="dropdown"><a href="#"><span>축구</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
								<ul>
									<li><a href="/board/list/4">국내 축구</a></li>
									<li><a href="/board/list/5">해외 축구</a></li>
								</ul></li>

							<li class="dropdown"><a href="#"><span>야구</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
								<ul>
									<li><a href="/board/list/6">국내 야구</a></li>
									<li><a href="/board/list/7">해외 야구</a></li>
								</ul></li>

							<li><a href="/board/list/8">E-SPORTS</a></li>
						</ul></li>

					<li class="dropdown"><a href="#"><span>커뮤니티</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
						<ul>
							<li class="dropdown"><a href="#"><span>음악</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
								<ul>
									<li><a href="/board/list/9">국내 음악</a></li>
									<li><a href="/board/list/10">해외 음악</a></li>
								</ul></li>

							<li class="dropdown"><a href="#"><span>미술</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
								<ul>
									<li><a href="/board/list/11">동양 미술</a></li>
									<li><a href="/board/list/12">서양 미술</a></li>
								</ul></li>

							<li><a href="/board/list/13">영화</a></li>
						</ul></li>
						
					<c:if test="${sessionScope.role_num == 1 || sessionScope.role_num == 2 }">
					<li class="dropdown"><a href="#"><span>관리자</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
						<ul>
		
							<li><a href="/manage/userList">회원 관리</a></li>

							<li><a href="/manage/boardList">게시판 관리</a></li>
						</ul></li>	
					</c:if>


				</ul>
			</nav>
			<!-- .navbar -->

			<div class="position-relative">
			<c:choose>
				<c:when test="${empty sessionScope.user_num }">
						<a href="/user/login" class="hover-login" data-text="로그인"><img src="/assets/img/login.png" style="width: 25px; margin: 0 7px 0 7px;" alt="로그인"></a>
						<a href="/user/joinCheck" class="hover-join" data-text="회원가입"><img src="/assets/img/join.png" style="width: 25px; margin: 0 7px 0 7px;" alt="회원가입"></a>
				</c:when>
				
				<c:otherwise>
					<a href="/mypage" class="hover-mypage" data-text="마이페이지"><img src="/assets/img/edit.png" style="width: 25px; margin: 0 7px 0 7px;" alt="마이페이지"></a>
					<a href="/user/logout" class="hover-logout" data-text="로그아웃"><img src="/assets/img/logout.png" style="width: 25px; margin: 0 7px 0 7px;" alt="로그아웃"></a>
				</c:otherwise>
			</c:choose>


				<a href="#" class="mx-2 js-search-open"> </a> <i class="bi bi-list mobile-nav-toggle"></i>

				<!-- ======= Search Form ======= -->
				<div class="search-form-wrap js-search-form-wrap">
					<form action="search-result.html" class="search-form">
						<span class="icon bi-search"></span> <input type="text" placeholder="Search" class="form-control">
						<button class="btn js-search-close">
							<span class="bi-x"></span>
						</button>
					</form>
					<!--         </div>End Search Form -->

				</div>

			</div>
		</div>
		

	</header>
	<!-- End Header -->