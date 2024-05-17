<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

  <main id="main">

    <!-- ======= Hero Slider Section ======= -->
    <section id="hero-slider" class="hero-slider">
      <div class="container-md aos-init aos-animate" data-aos="fade-in">
        <div class="row">
          <div class="col-12">
            <div class="swiper sliderFeaturedPosts">
            
              <div class="swiper-wrapper" id="recommendList">
              </div>
              
              <div class="custom-swiper-button-next">
                <span class="bi-chevron-right"  style="color : black"></span>
              </div>
              <div class="custom-swiper-button-prev">
                <span class="bi-chevron-left"  style="color : black"></span>
              </div>

              <div class="swiper-pagination"></div>
            </div>
          </div>
        </div>
      </div>
    </section><!-- End Hero Slider Section -->

    <!-- ======= Post Grid Section ======= -->
    <section id="posts" class="posts">
      <div class="container" data-aos="fade-up">
        <div class="row g-5">
        	
        	<div class="col-lg-4">
				<div id="popList1"></div>        
			</div>
          
          <div class="col-lg-8">
            <div class="row g-5">
           <div class="col-lg-4 border-start custom-border">
             <div id="popList2"></div>
            </div>
            
            <div class="col-lg-4 border-start custom-border">
             <div id="popList3"></div>
            </div>
            
              <!-- Trending Section -->
              <div class="col-lg-4">

                <div class="trending">
                  <h3>최신 글</h3>
                  <ul class="trending-post" id="newList">

                  </ul>
                </div>

              </div> <!-- End Trending Section -->
            </div>
          </div>

        </div> <!-- End .row -->
      </div>
    </section> <!-- End Post Grid Section -->


  </main><!-- End #main -->
  
  <script >
  var msg = "${msg}"
//   var user = "${user.role_name}"

  	if(msg != ""){
  		alert(msg);
  	}
  
// 	if(user != ""){
//   		alert(user);
//   	}
  </script>
<script src="/assets/js/index.js"></script>
<%@include file="layout/footer.jsp"%>
