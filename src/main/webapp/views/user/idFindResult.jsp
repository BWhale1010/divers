<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section id="contact" class="contact mb-5">
		<div class="container" data-aos="fade-up">

			<div class="col-lg-12 text-center mb-5">
				<h1 class="page-title">아이디 찾기</h1>
			</div>
			<div class="col-md-8 offset-md-2">
				<div class="form mt-5">
					<form action="/join" method="post" role="form" class="php-email-form">

					<div>
						<label for="username" style="margin-bottom: 10px">아이디</label>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="username"  value="${username }" readonly="readonly"> 					
						</div>
					</div>
					
					<div>
						<label for="email" style="margin-bottom: 10px">이메일</label>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="email"  value="${email }" readonly="readonly"> 					
						</div>
					</div>


						<br> <br> <br>

						<div class="btn-group d-flex justify-content-center">
							<div class="text-center">
								<button type="button" onclick="location.href='/';">취소</button>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="text-center">
								<button onclick="location.href='/user/pwReset'" type="button">비밀번호 찾기</button>
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


<%@include file="../layout/footer.jsp"%>