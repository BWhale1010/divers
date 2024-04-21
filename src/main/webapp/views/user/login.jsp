<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<main id="main">
	<section id="contact" class="contact mb-5">
		<div class="container" data-aos="fade-up">

			<div class="col-lg-12 text-center mb-5">
				<h1 class="page-title">로그인</h1>
			</div>
			<div class="col-md-8 offset-md-2">
				<div class="form mt-5">
					<form action="/user/loginProc" method="post" role="form" class="php-email-form">

						<div class="row">
							<label for="username" style="margin-bottom: 10px">아이디</label>
							<div class="form-group col-md-6">
								<input type="text" class="form-control" id="username"  name="username" placeholder="아이디를 입력해주세요." required>
							</div>
						</div>

						<div class="row">
							<label for="password" style="margin-bottom: 10px">비밀번호</label>
							<div class="form-group col-md-6">
								<input type="password" class="form-control" id="password"  name="password"  placeholder="비밀번호를 입력해주세요." required>
							</div>
						</div>
						
						<br> <br> 

						<div class="btn-group d-flex justify-content-center">
							<div class="text-center">
								<button type="button" onclick="location.href='/';">취소</button>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="text-center">
								<button id="login-btn" type="submit" >로그인</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>



	</section>

</main>
<!-- End #main -->
<script>
var msg = "${msg}"

   	if(msg != ""){
   		alert(msg);
   		<% request.getSession().removeAttribute("msg");%>
   	}
msg = null;
</script>


<%@include file="../layout/footer.jsp"%>