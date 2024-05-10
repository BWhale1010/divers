var total = 5;
var search_username = '';
var sort = '';
var direction = '';
var page = 1;
listAdd(page, search_username, sort, direction);

function listAdd(page, search_username, sort, direction){
	console.log("page : "+page);
	console.log("search_username : "+search_username);
	console.log("sort : "+sort);
	console.log("direction : "+direction);

	$.ajax({
		type : 'post',
		url : '/manage/listAdd',
		data : {'page': page,	'search_username':search_username,
					'sort':sort, 'direction':direction
		},
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
	if (item.role_num == 3) {
	    switch (item.state_num) {
	        case 1:
	            stateOptions = '<option value="1" selected>정상</option><option value="2">정지</option><option value="3" disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
	            break;
	        case 2:
	            stateOptions = '<option value="1">정상</option><option value="2" selected>정지</option><option value="3" disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
	            break;
	        case 3:
	        case 4:
	            stateOptions = '<option value="1" disabled>정상</option><option value="2" disabled>정지</option><option value="3" selected disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
	            break;
	    }
	} else {
	    switch (item.state_num) {
	        case 1:
	            stateOptions = '<option value="1" selected disabled>정상</option><option value="2" disabled>정지</option><option value="3" disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
	            break;
	        case 2:
	            stateOptions = '<option value="1" disabled>정상</option><option value="2" selected disabled>정지</option><option value="3" disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
	            break;
	        case 3:
	        case 4:
	            stateOptions = '<option value="1" disabled>정상</option><option value="2" disabled>정지</option><option value="3" selected disabled>탈퇴 대기</option><option value="4" disabled>탈퇴</option>';
	            break;
	    }
	}

        
        var report = '';
        if(item.role_num == 3){
			report = '<div class="col-md-3" style="position: relative;"><div style="position : absolute; top:50%; left:50%; transform: translate(-50%, -50%);">'+
        '<div><span style="margin-right: 10px;">신고 횟수 : '+item.reportCount+'번</span></div><div style="margin-top: 20px;"><span style="margin-right: 10px;">정지 횟수 : '+item.sspsCount+'번</span></div></div></div>';
		}

        // content 변수에 문자열 추가
        content += '<div class="row" style="margin-top:15px;"><div class="col-md-3" style="display: flex; flex-direction: column; align-items: center;">'+
        '<a href="" style="display: flex; align-items: center;"><img src="'+profileImg+'" style="width:150px; margin-bottom:15px; border-radius:50%;"></a>'+
        '<span style="margin-right: 10px;">아이디: '+item.username+'</span></div>'+
        '<div class="col-md-3" style="position: relative;"><div style="position : absolute; top:50%; transform: translate(0, -50%);">'+
        '<div class="post-meta"><span class="date">회원번호 : '+item.user_num+'</span> <span class="mx-1">•</span> <span>가입일 : '+item.join_date+'</span>'+
        '</div><div><span style="margin-right: 10px;">이메일 : '+item.email+'</span></div>'+
        '<div><span style="margin-right: 10px;">닉네임 : '+item.nickname+'</span></div><div>'+
        '<span>게시글 : '+item.postCount+'개 </span><span class="mx-1">•</span><span style="margin-right: 10px;">댓글 : '+item.commentCount+'개</span></div></div></div>'+
        '<div class="col-md-3" style="position: relative;"><div style="position : absolute; top:50%; left:50%; transform: translate(-50%, -50%);">'+
        '<div class="form-group d-flex flex-column"><label for="category">회원 등급</label><select id='+item.username+' onchange="roleChange(this.id, this.value)">' + roleOptions + '</select></div>'+
        '<div class="form-group d-flex flex-column" style="margin-top: 10px;"><label for="category">회원 상태</label><select id='+item.username+' onchange="stateChange(this.id, this.value)">' + stateOptions + '</select></div></div></div>'+
        report+'</div>';
    });

    // content를 #userList에 추가
    $("#userList").empty().append(content);
}

function roleChange(username, role_num){
	console.log("username : "+username);
	console.log("role_num : "+role_num);
	var role_name = '';
	if(role_num == 2){
		role_name = "관리자";
	}else if(role_num == 3){
		role_name = "회원";
	}
	
	var check = confirm("해당 유저(아이디 :  "+username+")의 회원 등급을 "+role_name+"으로 변경하시겠습니까?");
	let param = {'username':username, 'role_num':role_num};
	if(check){
		$.ajax({
			type : 'post', 
			url : '/manage/userRole',
			data : param,
			dataType : 'json',
			success : function(data){
				if(data > 0){
					listAdd(1, search_username);
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}

function stateChange(username, state_num){
	var state_name = '';
	if(state_num == 1){
		state_name = "정상";
	}else if(state_num == 2){
		state_name = "정지";
	}
	var check = confirm("해당 유저(아이디 :  "+username+")의 회원 상태를 "+state_name+"으로 변경하시겠습니까?")
	let param = {'username': username, 'state_num':state_num};
	if(check){
		$.ajax({
			type : 'post',
			url : '/manage/userState',
			data : param,
			dataType : 'json',
			success : function(data){
				if(data > 0){
					listAdd(1, search_username);
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
	
}

var sortDirections = {
    join: 'asc',
    report: 'asc',
    suspend: 'asc'
};

function sortUsers(sortBy) {
	console.log("sort : "+sortBy)
    var sortSpan = $('#' + sortBy + 'Sort');
    var sortDirection = sortDirections[sortBy];

    if (sortDirection === 'asc') {
        sortSpan.text('↓');
        sortDirections[sortBy] = 'desc';
    } else {
        sortSpan.text('↑');
        sortDirections[sortBy] = 'asc';
    }
    
	listAdd(page, search_username, sortBy, sortDirections[sortBy]);
}

$("#search-btn").on("click", function(){
	search_username = $("#search-word").val();
	console.log("username : "+search_username);
	
	listAdd(page, search_username, sort, direction);
})
