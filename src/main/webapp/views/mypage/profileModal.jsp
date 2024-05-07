<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      <form action="/mypage/profile" method="post" enctype="multipart/form-data">
      <div class="modal-body">
        <!-- 프로필 사진 변경 폼 -->
        	
        	<c:if test="${user.new_filename == null }">
        		<img src="/assets/img/profile.png" style="width:200px; margin-bottom:40px;" id="profilePreview">
        	</c:if>
        	<c:if test="${user.new_filename != null }">
        		<img src="/photo/${user.new_filename }" style="width:200px; margin-bottom:40px;" id="profilePreview">
        	</c:if>
        	
        	
        	<div>
        		<label classname="input-file-button" for="profileImg" class="btn btn-primary">업로드</label>
        		<input type="file" name="profileImg"  id="profileImg" style="display: none;" onchange="imgUrl(this);"/>
        		<input type="hidden" name="proImg">
        	</div>
            
            
        <input id="user_num" type="hidden" value="${sessionScope.user_num }">
      </div>
      
      <div class="modal-footer row">
      	<div class="col">
      		<button onclick="delProfile()" type="button" class="btn btn-danger" data-dismiss="modal">이미지 삭제</button>
      	</div>
      	<div class="col text-end">
      		<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        	<button type="submit" class="btn btn-primary">저장</button>
      	</div>
      </div>
      </form>
    </div>
  </div>
</div>
<script src="/assets/js/mypage.js"></script>