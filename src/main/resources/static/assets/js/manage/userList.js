var total = 5;
var search_username = '';

listAdd(1, search_username)

function listAdd(page, search_username){

	$.ajax({
		type : 'post',
		url : '/manage/listAdd',
		data : {'page': page,	'search_username':search_username},
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
    console.log(list);
    list.forEach(function(item){
        var profileImg = item.new_filename === null ? '/assets/img/profile.png' : "/photo/" + item.new_filename;

        // 회원 등급 옵션 생성
        var roleOptions = '';
        if(item.role_num == 1){
            roleOptions = '<option value="1" selected disabled>최고 관리자</option><option value="2" disabled>관리자</option><option value="3" disabled>회원</option>';
        }else if(item.role_num == 2){
            roleOptions = '<option value="1" disabled>최고 관리자</option><option value="2"  selected>관리자</option><option value="3">회원</option>';
        }else if(item.role_num == 3){
            roleOptions = '<option value="1" disabled>최고 관리자</option><option value="2" selected>관리자</option><option value="3" selected>회원</option>';
        }

        // 회원 상태 옵션 생성
        var stateOptions = '';
        if(item.state_num == 1){
            stateOptions = '<option value="1" selected>정상</option><option value="2">정지</option><option value="3" disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
        }else if(item.state_num == 2){
            stateOptions = '<option value="1">정상</option><option value="2" selected>정지</option><option value="3" disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
        }else if(item.state_num  == 3){
            stateOptions = '<option value="1" disabled>정상</option><option value="2" disabled>정지</option><option value="3" selected disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
        }else if(item.state_num  == 4){
            stateOptions = '<option value="1" disabled>정상</option><option value="2" disabled>정지</option><option value="3" selected disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
        }

        // content 변수에 문자열 추가
        content += '<div class="row" style="margin-top:15px;"><div class="col-md-4" style="display: flex; flex-direction: column; align-items: center;">'+
        '<a href="" style="display: flex; align-items: center;"><img src="'+profileImg+'" style="width:150px; margin-bottom:15px; border-radius:50%;"></a>'+
        '<span style="margin-right: 10px;">아이디: '+item.username+'</span></div>'+
        '<div class="col-md-4" style="position: relative;"><div style="position : absolute; top:50%; transform: translate(0, -50%);">'+
        '<div class="post-meta"><span class="date">회원번호 : '+item.user_num+'</span> <span class="mx-1">•</span> <span>가입일 : '+item.join_date+'</span>'+
        '</div><div><span style="margin-right: 10px;">이메일 : '+item.email+'</span></div>'+
        '<div><span style="margin-right: 10px;">닉네임 : '+item.nickname+'</span></div><div>'+
        '<span>게시글 : '+item.postCount+'개 </span><span class="mx-1">•</span><span style="margin-right: 10px;">댓글 : '+item.commentCount+'개</span></div></div></div>'+
        '<div class="col-md-4" style="position: relative;"><div style="position : absolute; top:50%; transform: translate(0, -50%);">'+
        '<div class="form-group d-flex flex-column"><label for="category">회원 등급</label><select>' + roleOptions + '</select></div>'+
        '<div class="form-group d-flex flex-column" style="margin-top: 10px;"><label for="category">회원 상태</label><select>' + stateOptions + '</select></div></div></div></div>';
    });

    // content를 #userList에 추가
    $("#userList").empty().append(content);
}




