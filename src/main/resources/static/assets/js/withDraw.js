var userNum = "";

function withDraw(user_num){
	var role_num = $("#role_num").val();
	
	if(role_num == 3){
		var check = confirm("회원 탈퇴를 진행하시겠습니까?");
		userNum = user_num;
		if(check){
		$('#withdrawModal').modal("show");	
		}
	}else{
		alert("관리자는 탈퇴를 할 수 없습니다.");
	}
	

}

function withCheck(){
	
	var check = confirm("회원 탈퇴를 진행하시겠습니까?");
	var password = $("#passwordWith").val();
	
	if(password == ""){
		$(".msg3").html("비밀번호를 입력해 주세요.")
	}else{
		$.ajax({
			type : 'post',
			url : '/mypage/passwordMatch',
			data : {'user_num':userNum, 'password':password},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					if(check){
						withDrawComplete(userNum);
					}
				}else{
					alert("비밀번호가 다릅니다.");
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}

function withDrawComplete(user_num){
	$.ajax({
		type : 'post',
		url : 'mypage/withDraw',
		data : {'user_num':user_num},
		dataType : 'json',
		success : function(data){
			if(data == 1){
				alert("탈퇴가 완료되었습니다. 탈퇴대기(7일) 중 로그인 시 탈퇴가 취소됩니다.");
				location.href = "/user/logout";
			}
		},
		error : function(e){
			console.log(e);
		}
	})
}