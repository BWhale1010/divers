<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade"  id="withdrawModal" tabindex="-1" role="dialog" aria-labelledby="withdrawModalLabel" aria-hidden="true" >
  <div class="modal-dialog" role="document" >
    <div class="modal-content">
      <div class="modal-header d-flex justify-content-between">
        <h5 class="modal-title" id="pwModalLabel">회원 탈퇴</h5>
        <button style="margin-left:20px;" type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="changeProfile.jsp" method="post" enctype="multipart/form-data">
      <div class="modal-body">
        <!-- 프로필 사진 변경 폼 -->
					<div class="row">

							<label for="password" style="margin-bottom: 10px">비밀번호 확인</label>
							<div class="form-group col-md-8 mb-3">					
									<input type="password" class="form-control" id="passwordWith" placeholder="비밀번호를 입력해주세요." required> 
									<span class="msg3"></span>								
							</div>
						</div>
        
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button onclick="withCheck()" type="button" class="btn btn-primary" data-dismiss="modal">탈퇴</button>
      </div>
      </form>
    </div>
  </div>
</div>
<script src="/assets/js/withDraw.js"></script>