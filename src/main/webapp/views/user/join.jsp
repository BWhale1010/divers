<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section id="contact" class="contact mb-5">
		<div class="container" data-aos="fade-up">

			<div class="col-lg-12 text-center mb-5">
				<h1 class="page-title">회원가입</h1>
			</div>
			<div class="col-md-8 offset-md-2">
				<div class="form mt-5">
					<form action="/join" method="post" role="form" class="php-email-form">

					<div>
						<label for="username" style="margin-bottom: 10px">아이디</label>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="username" placeholder="아이디를 입력해주세요." required> 
							<span class="msg1"></span>
						</div>
					</div>

						<div class="row">
							<label for="password" style="margin-bottom: 10px">비밀번호</label>
							<div class="form-group col-md-6">
								<input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요." required> 
								<span class="msg2 d-flex"></span>
							</div>

							<label for="password" style="margin-bottom: 10px">비밀번호 확인</label>
							<div class="form-group col-md-6">					
									<input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 한 번 더 입력해주세요." required> 
									<span class="msg3"></span>								
							</div>
						</div>

						<label for="email" style="margin-bottom: 10px">이메일</label>
						<div class="form-group d-flex flex-column">

							<div class="align-horizontal">
								<input type="text" class="form-control" id="email1" placeholder="이메일을 입력해주세요." required> 
								&nbsp;&nbsp; <span class="input-group-text"><i style="font-size: 20px">@</i></span> &nbsp;&nbsp;	
								<select id="email2" class="form-control" >
									<option value="init">선택해주세요</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>									
									<option value="gmail.com">gmail.com</option>							
									<option value="nate.com">nate.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="free">직접입력</option>
								</select>
							</div>

							<div class="d-flex justify-content-between">
								<div class="msg4"></div>
								<div id="customInput" class="align-horizontal"></div>
							</div>

						</div>

						<label for="emailChk" style="margin-bottom: 10px">이메일 인증</label>
						<div class="row">
							<div class="form-group col-md-6">
								<input type="text" class="form-control" id="emailCheck" placeholder="인증번호를 입력해주세요." required> 
								<span class="msg5 d-flex"></span>
							</div>

							<div class="form-group col-md-6">
								<button type="button" id="email-btn" class="ml-5">인증번호 받기</button>
							</div>
						</div>


						<label for="nickname" style="margin-bottom: 10px">닉네임</label>
						<div class="row">
							<div class="form-group col-md-6">
								<input type="text" class="form-control" id="nickname" placeholder="닉네임을 입력해주세요." required>
								<span class="msg6 d-flex"></span>
							</div>

							<div class="form-group col-md-6">
								<button type="button" id="nickname-btn"  class="ml-5" >닉네임 추천</button>
							</div>
						</div>



						<br> <br> <br>

						<div class="btn-group d-flex justify-content-center">
							<div class="text-center">
								<button type="button" onclick="location.href='/';">취소</button>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="text-center">
								<button id="join-btn"  type="button">회원 가입</button>
							</div>
						</div>

					</form>
				</div>
			</div>
			<!-- End Contact Form -->

		</div>
	</section>

</main>
<!-- End #main -->
<script src="/assets/js/join.js"></script>
<script src="/assets/js/nickname.js"></script>
<%@include file="../layout/footer.jsp"%>