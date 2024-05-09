var total = 5;
var search_username = '';

function listAdd(page, search_username){
	let param = {
		'page': page,
		'search_username':search_username
	}
	$.ajax({
		type : 'post',
		url : '/manage/listAdd',
		data : param,
		dataType : 'json',
		success : function(data){
			if(data.list.length == 0 && search_username != ''){
				alert("해당 검색어로 검색된 회원이 없습니다.")
				location.href="/manage/userList";
			}
			
			listDraw(data.list);
			
			if(total != data.total){
				total = data.total;
				$('#pagination').twbsPagination('destroy');
			}
			
			$('#pagination').twbsPagination({
				startPage : 1,
				totalPages: total,
				visiblePages: 5,
				onPageClick: function(e,page){
				console.log(e);
				listAdd(page, search_username);
				}
			});
		},
		error : function(e){
			console.log(e);
		}
	})
	
}

function listDraw(list){
	var content = "";
	
	list.foreach(function(item){
		content += '<div class="row"><div class="col-md-4" style="display: flex; flex-direction: column; align-items: center;">'+
		'<a href="" style="display: flex; align-items: center;"><img src="/assets/img/profile.png" style="width:150px; margin-bottom:15px;"></a>'+
		'<span style="margin-right: 10px;">아이디: aaa111</span></div>'+
		'<div class="col-md-4" style="position: relative;"><div style="position : absolute; top:50%; transform: translate(0, -50%);">'+
		'<div class="post-meta"><span class="date">회원번호 : 1</span> <span class="mx-1">•</span> <span>가입일 : 2024.05.09</span>'+
		'</div><div><span style="margin-right: 10px;">이메일 : email@email.com</span></div>'+
		'<div><span style="margin-right: 10px;">닉네임 : email</span></div><div>'+
		'<span>게시글 : 00개 </span>	<span class="mx-1">•</span><span style="margin-right: 10px;">댓글 : 00개</span></div></div></div>'+
		'<div class="col-md-4" style="position: relative;"><div style="position : absolute; top:50%; transform: translate(0, -50%);">'+
		'<div class="form-group d-flex flex-column"><label for="category">회원 등급</label><select><option value="1">최고 관리자</option>'+
		'<option value="2">관리자</option><option value="3">회원</option></select></div>'+
		'<div class="form-group d-flex flex-column" style="margin-top: 10px;"><label for="category">회원 상태</label><select>'+
		'<option value="1">정상</option><option value="2">정지</option><option value="3">탈퇴 대기</option>'+
		'<option value="4">탈퇴</option></select></div></div></div></div>'
		
		$("#userList").empty().append(content);
	})
}
