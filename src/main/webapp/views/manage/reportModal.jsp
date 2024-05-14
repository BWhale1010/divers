<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="reportModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    
      <div class="modal-header d-flex justify-content-between">
        <h5 class="modal-title" id="reportModalLabel">신고 내용</h5>
        <button style="margin-left:20px;" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <form action="/manage/#" method="post" enctype="multipart/form-data">
      
      <div class="modal-body">   	
		<div class="row" >
			<div class="col-md-12 post-content" data-aos="fade-up">
				<div class="single-post" id="reportInfo">
              	
              	</div>
              	
			</div>
		</div>
      </div>
      
      <div class="modal-footer row">
      	<div class="col">
      		<button onclick="suspUser();" type="button" class="btn btn-danger" data-dismiss="modal">회원 정지</button>
      		<button onclick="blind();;" type="button" class="btn btn-danger" data-dismiss="modal">블라인드</button>
      	</div>
      	<div class="col text-end">
      		<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      	</div>
      </div>
      </form>
    </div>
  </div>
</div>
<script src="/assets/js/manage/reportModal.js"></script>
