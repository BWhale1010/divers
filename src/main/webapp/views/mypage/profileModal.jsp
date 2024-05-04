<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="profileModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header d-flex justify-content-between">
        <h5 class="modal-title" id="profileModalLabel">프로필 사진 변경</h5>
        <button style="margin-left:20px;" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="changeProfile.jsp" method="post" enctype="multipart/form-data">
      <div class="modal-body">
        <!-- 프로필 사진 변경 폼 -->
        
        	<img src="/assets/img/profile.png" style="width:200px; margin-bottom:40px;" id="profilePreview">
        	
        	<div>
        		<label classname="input-file-button" for="profileImg" class="btn btn-primary">업로드</label>
        		<input type="file" name="profileImg"  id="profileImg" style="display: none;" onchange="imgUrl(this);"/>
        		<input type="hidden" name="profileImg">
        	</div>
            
            
        
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="submit" class="btn btn-primary">저장</button>
      </div>
      </form>
    </div>
  </div>
</div>
<script src="/assets/js/mypage.js"></script>