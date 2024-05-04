<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section id="contact" class="contact mb-5">
		<div class="container" data-aos="fade-up">

			<div class="col-lg-12 text-center mb-5">
				<h1 class="page-title">비밀번호 재설정</h1>
			</div>
			<div class="col-md-8 offset-md-2">
				<div class="form mt-5">
					<form action="/user/idFind" method="post" role="form" class="php-email-form">
					
					<div>
						<label for="username" style="margin-bottom: 10px">아이디</label>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="username"  value="${username }" readonly="readonly"> 
							<span class="msg1"></span>
						</div>
					</div>
					
					<div>
						<label for="email" style="margin-bottom: 10px">이메일</label>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="email"  value="${email }" readonly="readonly"> 
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

						<br> <br> 

						<div class="btn-group d-flex justify-content-center">
							<div class="text-center">
								<button type="button" onclick="location.href='/';">취소</button>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="text-center">
								<button id="idFind-btn" type="button"  onclick="pwReset()">비밀번호 재설정</button>
							</div>
						</div>
						
						<br>
						
					</form>
				</div>
			</div>
		</div>



	</section>

</main>
<!-- End #main -->
<script>

</script>

<script src="/assets/js/pwResetResult.js"></script>
<%@include file="../layout/footer.jsp"%>