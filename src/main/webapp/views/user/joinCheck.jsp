<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/views/layout/header.jsp"%>

  <main id="main">
    <section id="contact" class="contact mb-5">
      <div class="container" data-aos="fade-up">

        <div class="row">
          <div class="col-lg-12 text-center mb-5">
            <h1 class="page-title">이용 약관 동의</h1>
          </div>
        </div>


        <div class="form mt-5">
          <div class="php-email-form">
			
			<p style="font-size:20px;">
				divers를 방문해 주셔서 감사합니다. 
				<br>
				<br>
				본 divers 사이트는 <strong style="color:red;">포트폴리오용</strong>으로 실제 서비스되는 사이트가 아닙니다.
				이 약관은 포트폴리오용 예시 약관으로 divers 프로젝트 설명을 위해 작성되었습니다.
				<br>
				<br>
				본 divers 사이트는 프로젝트용이며 아이디, 비밀번호, 이메일, 닉네임을 입력받아 회원가입을 진행하고 있습니다.
				<br>
				스프링 시큐리티를 사용하여 비밀번호를 해시화하고 있으나 <strong style="color:red;">보안 상 위험성이 있을 수 있으므로 실제 사용하는 아이디와 비밀번호를 입력하지 말아주세요.</strong>
				<br>
				회원가입 시 실제 이메일을 입력받고 있으나 주소만 저장됨을 알려드립니다.
				<br>
				<br>
				<strong style="color:red;">테스트용 계정이 제공되니 페이지에서 확인해 주세요.</strong>
				<br>
				<hr>
				<br>
				1. 회원가입 시 수집
				<br>
					&nbsp;&nbsp;&nbsp;(1) 일반가입: 로그인 아이디, 비밀번호, 이메일, 닉네임
					<br>
					<br>
				<strong style="color:red;">2. 테스트용 계정</strong>
				<br>
					&nbsp;&nbsp;&nbsp;(1) 아이디 : testuser1(관리자), testuser2~5(일반회원)
					<br>
					&nbsp;&nbsp;&nbsp;(2) 비밀번호 : aaa111
				<br>
				<hr>
				<br>
				<br>
				
			</p>
			
			<div class="input-group mb-3">
  				<div class="input-group-prepend">
    				<div class="input-group-text">
    				  <input id="joinCheck" type="checkbox" style="width:15px;height: 15px;">
  				  </div>
  				</div>
 			 <p style="font-size:20px;">&nbsp;&nbsp;&nbsp;divers 회원가입에 동의 합니다.</p>
			</div>


 			
 			<div class="btn-group d-flex justify-content-center" > 			
 				<div class="text-center"><button type="button" onclick="location.href='/';">취소</button></div>
 				&nbsp;&nbsp;&nbsp;&nbsp;
          		<div id="joinCheck-btn" class="text-center"><button type="button">회원 가입</button></div>
            </div>

          </div>
        </div><!-- End Contact Form -->

      </div>
    </section>

  </main><!-- End #main -->
 <script src="/assets/js/join.js" type="text/javascript"></script>


<%@include file="/views/layout/footer.jsp"%>
  