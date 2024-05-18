<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    
      <div class="modal-header d-flex justify-content-between">
        <h5 class="modal-title" id="userModalLabel">유저 상세보기</h5>
        <button style="margin-left:20px;" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <input type="hidden" value="${sessionScope.role_num }" id="sessionRole">
      <form action="/manage/#" method="post" enctype="multipart/form-data">
      
      <div class="modal-body">   	
		<div class="row justify-content-center" >
			<div class="col-md-4 text-center">
				<div class="profile-picture mx-auto" id="profile"></div>
			</div>
			
			<div id="information"  class="row col-md-8" style="position: relative;"></div>
			<div id="boardList" class="row col-md-12 justify-content-center" style="margin-top: 25px;"></div>

		</div>
      </div>
      
      <div class="modal-footer row">
      	<div class="col">
      		<button onclick="suspUser()" type="button" class="btn btn-danger" data-dismiss="modal">회원 정지</button>
      	</div>
      	<div class="col text-end">
      		<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      	</div>
      </div>
      </form>
    </div>
  </div>
</div>
<script src="/assets/js/manage/userList.js"></script>
