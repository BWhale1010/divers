<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

  <main id="main">
    <section>
    <input id="boardId" type="hidden" value= ${boardId }>
      <div class="container">
        <div class="row">

          <div class="col-md-9" data-aos="fade-up">
            <h3 class="category-title">Category: Business</h3>

            <div class="d-md-flex post-entry-2 half">
              <a href="single-post.html" class="me-4 thumbnail">
                <img src="/assets/img/post-landscape-6.jpg" alt="" class="img-fluid">
              </a>
              <div>
                <div class="post-meta"><span class="date">Culture</span> <span class="mx-1">&bullet;</span> <span>Jul 5th '22</span></div>
                <h3><a href="single-post.html">What is the son of Football Coach John Gruden, Deuce Gruden doing Now?</a></h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio placeat exercitationem magni voluptates dolore. Tenetur fugiat voluptates quas, nobis error deserunt aliquam temporibus sapiente, laudantium dolorum itaque libero eos deleniti?</p>
                <div class="d-flex align-items-center author">
                  <div class="photo"><img src="/assets/img/person-2.jpg" alt="" class="img-fluid"></div>
                  <div class="name">
                    <h3 class="m-0 p-0">Wade Warren</h3>
                  </div>
                </div>
              </div>
            </div>

            <div class="d-md-flex post-entry-2 half">
              <a href="single-post.html" class="me-4 thumbnail">
                <img src="/assets/img/post-landscape-1.jpg" alt="" class="img-fluid">
              </a>
              <div>
                <div class="post-meta"><span class="date">Culture</span> <span class="mx-1">&bullet;</span> <span>Jul 5th '22</span></div>
                <h3><a href="single-post.html">What is the son of Football Coach John Gruden, Deuce Gruden doing Now?</a></h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio placeat exercitationem magni voluptates dolore. Tenetur fugiat voluptates quas, nobis error deserunt aliquam temporibus sapiente, laudantium dolorum itaque libero eos deleniti?</p>
                <div class="d-flex align-items-center author">
                  <div class="photo"><img src="/assets/img/person-2.jpg" alt="" class="img-fluid"></div>
                  <div class="name">
                    <h3 class="m-0 p-0">Wade Warren</h3>
                  </div>
                </div>
              </div>
            </div>

            <div class="d-md-flex post-entry-2 half">
              <a href="single-post.html" class="me-4 thumbnail">
                <img src="/assets/img/post-landscape-2.jpg" alt="" class="img-fluid">
              </a>
              <div>
                <div class="post-meta"><span class="date">Culture</span> <span class="mx-1">&bullet;</span> <span>Jul 5th '22</span></div>
                <h3><a href="single-post.html">What is the son of Football Coach John Gruden, Deuce Gruden doing Now?</a></h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio placeat exercitationem magni voluptates dolore. Tenetur fugiat voluptates quas, nobis error deserunt aliquam temporibus sapiente, laudantium dolorum itaque libero eos deleniti?</p>
                <div class="d-flex align-items-center author">
                  <div class="photo"><img src="/assets/img/person-2.jpg" alt="" class="img-fluid"></div>
                  <div class="name">
                    <h3 class="m-0 p-0">Wade Warren</h3>
                  </div>
                </div>
              </div>
            </div>

            <div class="d-md-flex post-entry-2 half">
              <a href="single-post.html" class="me-4 thumbnail">
                <img src="/assets/img/post-landscape-3.jpg" alt="" class="img-fluid">
              </a>
              <div>
                <div class="post-meta"><span class="date">Culture</span> <span class="mx-1">&bullet;</span> <span>Jul 5th '22</span></div>
                <h3><a href="single-post.html">What is the son of Football Coach John Gruden, Deuce Gruden doing Now?</a></h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio placeat exercitationem magni voluptates dolore. Tenetur fugiat voluptates quas, nobis error deserunt aliquam temporibus sapiente, laudantium dolorum itaque libero eos deleniti?</p>
                <div class="d-flex align-items-center author">
                  <div class="photo"><img src="/assets/img/person-2.jpg" alt="" class="img-fluid"></div>
                  <div class="name">
                    <h3 class="m-0 p-0">Wade Warren</h3>
                  </div>
                </div>
              </div>
            </div>

            <div class="d-md-flex post-entry-2 half">
              <a href="single-post.html" class="me-4 thumbnail">
                <img src="/assets/img/post-landscape-4.jpg" alt="" class="img-fluid">
              </a>
              <div>
                <div class="post-meta"><span class="date">Culture</span> <span class="mx-1">&bullet;</span> <span>Jul 5th '22</span></div>
                <h3><a href="single-post.html">What is the son of Football Coach John Gruden, Deuce Gruden doing Now?</a></h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio placeat exercitationem magni voluptates dolore. Tenetur fugiat voluptates quas, nobis error deserunt aliquam temporibus sapiente, laudantium dolorum itaque libero eos deleniti?</p>
                <div class="d-flex align-items-center author">
                  <div class="photo"><img src="/assets/img/person-2.jpg" alt="" class="img-fluid"></div>
                  <div class="name">
                    <h3 class="m-0 p-0">Wade Warren</h3>
                  </div>
                </div>
              </div>
            </div>
            


            <div class="text-start py-4">
              <div class="custom-pagination" style="display: inline-block;">
                <a href="#" class="prev">Prevous</a>
                <a href="#" class="active">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#" class="next">Next</a>
              </div>
              
	             <div class="writeBtn" style="display: inline-block; float: right;">
	            	<button type="button" onclick="location.href='/board/write?boardId='+boardId">글쓰기</button>
	            </div>
            </div>
            

          </div>

          <div class="col-md-3">

		<%@include file="./sidebar.jsp" %>

<!--             <div class="aside-block"> -->
<!--               <h3 class="aside-title">Video</h3> -->
<!--               <div class="video-post"> -->
<!--                 <a href="https://www.youtube.com/watch?v=AiFfDjmd0jU" class="glightbox link-video"> -->
<!--                   <span class="bi-play-fill"></span> -->
<!--                   <img src="assets/img/post-landscape-5.jpg" alt="" class="img-fluid"> -->
<!--                 </a> -->
<!--               </div> -->
<!--             </div>End Video -->

            <div class="aside-block">
              <h3 class="aside-title">Categories</h3>
              <ul class="aside-links list-unstyled">
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Business</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Culture</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Sport</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Food</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Politics</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Celebrity</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Startups</a></li>
                <li><a href="category.html"><i class="bi bi-chevron-right"></i> Travel</a></li>
              </ul>
            </div><!-- End Categories -->

            <div class="aside-block">
              <h3 class="aside-title">Tags</h3>
              <ul class="aside-tags list-unstyled">
                <li><a href="category.html">Business</a></li>
                <li><a href="category.html">Culture</a></li>
                <li><a href="category.html">Sport</a></li>
                <li><a href="category.html">Food</a></li>
                <li><a href="category.html">Politics</a></li>
                <li><a href="category.html">Celebrity</a></li>
                <li><a href="category.html">Startups</a></li>
                <li><a href="category.html">Travel</a></li>
              </ul>
            </div><!-- End Tags -->

          </div>

        </div>
      </div>
    </section>
  </main><!-- End #main -->

<script src="/assets/js/category.js"></script>
<%@include file="../layout/footer.jsp"%>