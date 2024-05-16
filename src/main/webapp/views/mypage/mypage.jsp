<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section id="contact" class="contact mb-5">
		<div class="container" data-aos="fade-up">
			<div class="row">
				<div class="col-lg-12 text-center mb-5">
					<h1 class="page-title">마이페이지</h1>
				</div>
				<div class="col-md-8 offset-md-2">
					<div class="form mt-5">
						<form action="#" method="post" role="form" class="php-email-form">

							<div class="row justify-content-center">
								<div class="col-md-4 profile-margin text-center">
									<div class="profile-picture mx-auto">
										<c:if test="${user.new_filename == null }">
											<img src="/assets/img/profile.png" alt="프로필 사진" style="display: block;">
										</c:if>
										<c:if test="${user.new_filename != null }">
											<img src="/photo/${user.new_filename }" style="display: block;">
										</c:if>
										
									</div>
									<button style="margin-top: 10px; margin-bottom: 20px;" type="button" id="profile-btn" class="ml-5" data-toggle="modal" data-target="#profileModal">사진 변경</button>
			
								</div>
								<input type="hidden" value="${user.user_num }" id="userNum">	
								<div class="col-md-6">
									<div class="form-group">
										<label for="username" style="margin-bottom: 10px">아이디</label> 
										<input type="text" class="form-control" id="username" value="${user.username}" readonly="readonly">
									</div>
									<div class="form-group">
										<label for="email" style="margin-bottom: 10px">이메일</label> 
										<input type="text" class="form-control" id="email" value="${user.email}" readonly="readonly">
									</div>
									<div class="form-group">
										<label for="nickname" style="margin-bottom: 10px">닉네임</label> 
										<input type="text" class="form-control" id="nickname" value="${user.nickname}"> 
										<span class="msg5 d-flex"></span>
										<button style="margin-top: 10px" type="button" id="nickname-btn" class="ml-5">닉네임 추천</button>								
									</div>
									<div>
										<button type="button" id="pw-btn" class="ml-5" data-toggle="modal" data-target="#pwModal">비밀번호 변경</button>
									</div>
								</div>
							</div>

							<br>
							<br>
							<br>

							<div class="row justify-content-center">
								<div id="mypageButton" class="col-md-9 d-flex justify-content-between align-items-center">
									<button id="withdraw-btn" type="button" style="text-align: left;" onclick="withDraw(${user.user_num});">탈퇴</button>
									<div class="button-margin">
										<button type="button" onclick="location.href='/';">취소</button>
										<button id="save-btn" type="button">저장</button>
									</div>
								</div>
							</div>
		
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</section>


</main>
<!-- End #main -->
<script>
var msg = "${msg}";
if(msg != ""){
	alert(msg);
}
</script>
<script src="/assets/js/mypage.js"></script>
<%@ include file="./profileModal.jsp" %>
<%@ include file="./pwModal.jsp" %>
<%@ include file="./withdrawModal.jsp" %>
<%@include file="../layout/footer.jsp"%>
