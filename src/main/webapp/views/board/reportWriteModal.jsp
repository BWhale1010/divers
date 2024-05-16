<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="reportWriteModal" tabindex="-1" role="dialog" aria-labelledby="reportWriteModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    
      <div class="modal-header d-flex justify-content-between">
        <h5 class="modal-title" id="reportWriteModalLabel">신고 하기</h5>
        <button style="margin-left:20px;" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <form action="/manage/#" method="post" enctype="multipart/form-data">
      
      <div class="modal-body">
      <input type="hidden"  id="modalPost_num">
      <input type="hidden"  id="modalComment_num">
      <input type="hidden"  id="loginId">
		<div class="row" >
			<div class="col-md-12 post-content" data-aos="fade-up">
				<div>
					<label>신고 분류</label>
						<select id="reportSelect" class="form-control" >
							<option value="init">선택</option>
							<option value="1">홍보</option>
							<option value="2">성인물</option>
							<option value="3">기타</option>
						</select>
				</div>
				<div>
					<label>상세이유</label>
					<textarea id="reportDetail" class="form-control"  cols="30" rows="10" ></textarea>
				</div>
			</div>
		</div>
      </div>
      
      <div class="modal-footer row">
      	<div class="col text-end">
      		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      		<button id="report-btn" type="button" class="btn btn-primary"  data-bs-dismiss="modal">저장</button>
      	</div>
      </div>
      </form>
    </div>
  </div>
</div>
<script src="/assets/js/post.js"></script>
<!-- <script src="/assets/js/manage/reportModal.js"></script> -->