<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- ======= Footer ======= -->
  <footer id="footer" class="footer">

    <div class="footer-content">
      <div class="container">

        <div class="row g-5">
          <div class="col-lg-4">
            <h3 class="footer-heading">About Divers</h3>
            <p>이 프로젝트는 Spring Boot 프레임워크를 사용하고 MyBatis와 MySQL로 데이터베이스를 구성하였으며, Maven으로 빌드하였습니다. Tomcat 서버에서 운영되며, Oracle Cloud에 배포된 커뮤니티 프로젝트입니다.</p>
            <p><a href="https://github.com/BWhale1010/divers" class="footer-link-more">GIT HUB PAGE</a></p>
          </div>
          <div class="col-6 col-lg-2">
            <h3 class="footer-heading">Navigation</h3>
            <ul class="footer-links list-unstyled">
              <li><a href="/"><i class="bi bi-chevron-right"></i> Home</a></li>
              <c:choose>
              	<c:when test="${empty sessionScope.user_num }">
	            	<li><a href="/user/login"><i class="bi bi-chevron-right"></i> 로그인</a></li>
           			<li><a href="/user/joinCheck"><i class="bi bi-chevron-right"></i> 회원가입</a></li>	
              	</c:when>
              	<c:otherwise>
              		<li><a href="/mypage"><i class="bi bi-chevron-right"></i> 마이페이지</a></li>
           			<li><a href="/user/logout"><i class="bi bi-chevron-right"></i> 로그아웃</a></li>	
              	</c:otherwise>
              </c:choose>
              <c:if test="${sessionScope.role_num == 1 || sessionScope.role_num == 2 }">
	              <li><a href="single-post.html"><i class="bi bi-chevron-right"></i> 회원관리</a></li>
	              <li><a href="about.html"><i class="bi bi-chevron-right"></i> 게시판 관리</a></li>
              </c:if>
            </ul>
          </div>
          <div class="col-6 col-lg-2">
            <h3 class="footer-heading">Categories</h3>
            <ul class="footer-links list-unstyled">
              <li><a href="/board/list/3"><i class="bi bi-chevron-right"></i> 공지</a></li>
              <li><a href="/board/list/1"><i class="bi bi-chevron-right"></i> 유머</a></li>
              <li><a href="/board/list/2"><i class="bi bi-chevron-right"></i> 정보</a></li>
              <li><a href="/board/list/4"><i class="bi bi-chevron-right"></i> 국내축구</a></li>
              <li><a href="/board/list/6"><i class="bi bi-chevron-right"></i> 국내야구</a></li>
              <li><a href="/board/list/9"><i class="bi bi-chevron-right"></i> 국내음악</a></li>
              <li><a href="/board/list/11"><i class="bi bi-chevron-right"></i> 동양미술</a></li>
              <li><a href="/board/list/13"><i class="bi bi-chevron-right"></i> 영화</a></li>

            </ul>
          </div>

          <div class="col-lg-4">
            <h3 class="footer-heading">Recommend Posts</h3>

            <ul class="footer-links footer-blog-entry list-unstyled" id="footerList">

            </ul>

          </div>
        </div>
      </div>
    </div>

    <div class="footer-legal">
      <div class="container">

        <div class="row justify-content-between">
          <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
            <div class="copyright">
              Â© Copyright <strong><span>ZenBlog</span></strong>. All Rights Reserved
            </div>

            <div class="credits">
              <!-- All the links in the footer should remain intact. -->
              <!-- You can delete the links only if you purchased the pro version. -->
              <!-- Licensing information: https://bootstrapmade.com/license/ -->
              <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/herobiz-bootstrap-business-template/ -->
              Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>

          </div>

          <div class="col-md-6">
<!--             <div class="social-links mb-3 mb-lg-0 text-center text-md-end"> -->
<!--               <a href="#" class="twitter"><i class="bi bi-twitter"></i></a> -->
<!--               <a href="#" class="facebook"><i class="bi bi-facebook"></i></a> -->
<!--               <a href="#" class="instagram"><i class="bi bi-instagram"></i></a> -->
<!--               <a href="#" class="google-plus"><i class="bi bi-skype"></i></a> -->
<!--               <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a> -->
<!--             </div> -->

          </div>

        </div>

      </div>
    </div>

  </footer>

  <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="/assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="/assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="/assets/vendor/aos/aos.js"></script>
<!--   <script src="/assets/vendor/php-email-form/validate.js"></script> -->

  <!-- Template Main JS File -->
  <script src="/assets/js/main.js"></script>
<script src="/assets/js/footer.js"></script>
</body>

</html>