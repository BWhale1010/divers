var total = 5;
var search_username = "";
var sort = "";
var direction = "";
var page = 1;

var modalUser_num = '';
var roleNum = '';
var sessionRole = $("#sessionRole").val();

listAdd(page, search_username, sort, direction);

$("#search-btn").on("click", function(){
    var username = $("#search-word").val();
    search_username = username;
    console.log("검색 search_word : "+username);
    
     listAdd(1, search_username, sort, direction);
    
});

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
        var profileImg = item.new_filename === null ? '/assets/img/profile.png' : "/upload/" + item.new_filename;

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
			report = '<div class="col-md-3" style="position: relative;"><a data-toggle="modal" href="#userModal" data-usernum="'+item.user_num+'"><div style="position : absolute; top:50%; left:50%; transform: translate(-50%, -50%);">'+
        '<div><span style="margin-right: 10px;">신고 횟수 : '+item.reportCount+'번</span></div><div style="margin-top: 20px;"><span style="margin-right: 10px;">정지 횟수 : '+item.sspsCount+'번</span></div></div></a></div>';
		}

        // content 변수에 문자열 추가
        content += '<div class="row" style="margin-top:15px;"><div class="col-md-3" style="display: flex; flex-direction: column; align-items: center;">'+
        '<a data-toggle="modal" href="#userModal" data-usernum="'+item.user_num+'" style="display: flex; align-items: center;"><img src="'+profileImg+'" style="width:150px; margin-bottom:15px; border-radius:50%;"></a>'+
        '<span style="margin-right: 10px;">아이디: '+item.username+'</span></div>'+
        '<div class="col-md-3" style="position: relative;"><a data-toggle="modal" href="#userModal" data-usernum="'+item.user_num+'"><div style="position : absolute; top:50%; transform: translate(0, -50%);">'+
        '<div class="post-meta"><span class="date">회원번호 : '+item.user_num+'</span> <span class="mx-1">•</span> <span>가입일 : '+item.join_date+'</span>'+
        '</div><div><span style="margin-right: 10px;">이메일 : '+item.email+'</span></div>'+
        '<div><span style="margin-right: 10px;">닉네임 : '+item.nickname+'</span></div><div>'+
        '<span>게시글 : '+item.postCount+'개 </span><span class="mx-1">•</span><span style="margin-right: 10px;">댓글 : '+item.commentCount+'개</span></div></div></a></div>'+
        '<div class="col-md-3" style="position: relative;"><div style="position : absolute; top:50%; left:50%; transform: translate(-50%, -50%);">'+
        '<div class="form-group d-flex flex-column"><label for="category">회원 등급</label><select id='+item.user_num+' onchange="roleChange(this.id, this.value)">' + roleOptions + '</select></div>'+
        '<div class="form-group d-flex flex-column" style="margin-top: 10px;"><label for="category">회원 상태</label><select id='+item.user_num+' onchange="stateChange(this.id, this.value)">' + stateOptions + '</select></div></div></div>'+
        report+'</div>';
    });

    // content를 #userList에 추가
    $("#userList").empty().append(content);
}

function roleChange(user_num, role_num){
	if(sessionRole == 1){
			var role_name = '';
	if(role_num == 2){
		role_name = "관리자";
	}else if(role_num == 3){
		role_name = "회원";
	}
	
	var check = confirm("해당 유저의 등급을 "+role_name+"으로 변경하시겠습니까?");
	let param = {'user_num':user_num, 'role_num':role_num};
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
	}else{
		alert("회원 등급과 상태 변경은 최고관리자만 가능합니다.");
	}
}

function stateChange(user_num, state_num){
	if(sessionRole == 1){
	console.log(sessionRole);
	var state_name = '';
	if(state_num == 1){
		state_name = "정상";
	}else if(state_num == 2){
		state_name = "정지";
	}
	
	var check = confirm("해당 유저의 회원 상태를 "+state_name+"으로 변경하시겠습니까?");
	if(check){
		$.ajax({
			type : 'post',
			url : '/manage/userState',
			data : {'user_num': user_num, 'state_num':state_num},
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
	}else{
		alert("회원 등급과 상태 변경은 최고관리자만 가능합니다.");
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

$('#userModal').on('show.bs.modal', function (event) {
    var userNum = $(event.relatedTarget).data('usernum');
    modalUser_num = userNum;
    modalInfo(userNum);
});

function modalInfo(userNum) {
    $.ajax({
		type : 'post',
		url : '/manage/userInfo',
		data : {'user_num': userNum},
		dataType : 'json',
		success : function(data){
			
			modalDraw(data.info, data.post, data.comment, data.report);
		},
		error : function(e){
			console.log(e);
		}
		
	})
}

function modalDraw(info, post, comment, report) {
    console.log(info[0]);
    var content = "";
    var content1 = "";
    var profile = '';
    roleNum = info[0].role_num;
    
    if(info[0].new_filename == null){
		profile = '<img src="/assets/img/profile.png" alt="프로필 사진" style="display: block;">';
	}else if(info[0].new_filename != null){
		profile = '<img src="/upload/'+info[0].new_filename+'" style="display: block;">';
	}
	
	$("#profile").empty().append(profile);
    

    var roleOptions = '';
    if (info[0].role_num == 1) {
        roleOptions = '<option value="1" selected disabled>최고 관리자</option><option value="2" disabled>관리자</option><option value="3" disabled>회원</option>';
    } else if (info[0].role_num == 2) {
        roleOptions = '<option value="1" disabled>최고 관리자</option><option value="2" selected>관리자</option><option value="3">회원</option>';
    } else if (info[0].role_num == 3) {
        roleOptions = '<option value="1" disabled>최고 관리자</option><option value="2" selected>관리자</option><option value="3" selected>회원</option>';
    }

    var stateOptions = '';
    if (info[0].role_num == 3) {
        switch (info[0].state_num) {
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
        switch (info[0].state_num) {
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

    content += '<div class="col-md-6" style="position: relative;"><div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width:100%;">' +
        '<div><span>아이디 : ' + info[0].username + '</span></div><div><span>닉네임 : ' + info[0].nickname + '</span></div><div><span>이메일 : ' + info[0].email + '</span></div>' +
        '<div><span>정지 횟수 : ' + info[0].reportCount + '</span></div><div><span>신고 횟수 : ' + info[0].sspsCount + '</span></div></div></div>' +
        '<div class="col-md-6" style="position: relative"><div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 100%;"><div class="d-flex flex-column" style="width: 50%"><label>회원 등급</label>' +
        '<select id='+info[0].user_num+' onchange="roleChange(this.id, this.value)">' + roleOptions + '</select></div>' +
        '<div class="d-flex flex-column" style="margin-top: 10px; width: 50%;"><label>회원 상태</label><select id='+info[0].user_num+' onchange="stateChange(this.id, this.value)">' + stateOptions + '</select></div>' +
        '<div style="margin-top: 10px;" class="d-flex flex-column"><span><a id="toggleBoard" onclick="toggle(\'board\');" style="color:blue;">게시판 리스트</a> | <a id="toggleLog" onclick="toggle(\'log\');">회원 로그</a></span></div></div></div>';
        
        
       content1 += '<div class="col-md-6 text-left"><h4>게시글 리스트</h4><div class="scroll" style="margin-bottom:20px;">';

    post.forEach(function(item) {
        content1 += '<div><a href="/board/detail/'+item.post_num+'">' + item.title +' | '+ item.count +' | '+ item.board_date + '</a></div>' ;
    });
    
    content1 += '</div><div style="margin-top:20px; position:absolute; bottom: 0px;"><span>신고횟수 : ' +  report[0].postReportCount + '번</span></div></div>'+
    '<div class="col-md-6 text-right"><h4>댓글 리스트</h4><div class="scroll"  style="margin-bottom:10px;">';

    comment.forEach(function(item) {
        content1 += '<div><a href="/board/detail/'+item.post_num+'">' + item.comment +' | '+ item.thumbCommentCount +' | '+ item.comment_date + '</a></div>';
        
    });
    
    content1 += '</div><div style="margin-top:20px; position:absolute; bottom: 0px;"><span>신고횟수 : ' + report[0].commentReportCount + '</span></div></div>';

    $("#information").empty().append(content);
    $("#boardList").empty().append(content1);
} 

function toggle(sort){
	var toggleBoard = document.getElementById('toggleBoard');
    var toggleLog = document.getElementById('toggleLog');
	
	if(sort == 'board'){
		toggleBoard.classList.add('active');
		toggleLog.classList.remove('active');
		$("#toggleBoard").css("color", "blue");
		$("#toggleLog").css("color", "");
		
		modalInfo(modalUser_num);
		
	}else{
		toggleBoard.classList.remove('active');
		toggleLog.classList.add('active');
		$("#toggleBoard").css("color", "");
		$("#toggleLog").css("color", "blue");
		
		$.ajax({
			type : 'post',
			url : '/manage/userLog',
			data : {'user_num':modalUser_num},
			dataType : 'json',
			success : function(data){
				userLog(data.log);
				
			},
			error : function(e){
				console.log(e);
			}
		})		
	}
}


function userLog(log){
	var content = '';
	content += '<div class="col-md-6 text-center"><h4>로그 리스트</h4><div class="scroll" style="margin-bottom:20px;">';
	
	log.forEach(function(item){
		content+= '<div>'+item.log_num+' | '+item.sort_name+' | '+item.alter_name+' | '+item.alter_date+'</div>';
	})
	
	content += '</div></div>';
	
	$("#boardList").empty().append(content);
}

function suspUser(){
	console.log(sessionRole);

		if(roleNum == 3){
		console.log("aaaaa : "+user_num);
		var check = confirm("해당 유저를 일시정지 처리하시겠습니까?");
	
			if(check){
				$.ajax({
					type : 'post',
					url : '/manage/suspUser',
					data : {'user_num':modalUser_num},
					dataType : 'json',
					success : function(data){
						if(data == 1){
							alert("해당 유저에 대한 일시정지가 완료되었습니다. ");
						}
					},
					error : function(e){
						console.log(e);
					}
				})
			}
	}else{
		alert("관리자는 일시정지가 불가능합니다.");
	}

}