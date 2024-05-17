var firstNickname = null;
var newNickname = null;
var nicknameChk= false;
var passwordChk = false;
var password = false;
var passwordOriChk = false;

function groSpace(val){
	var newval = val.replace(/\s/g,"");
		
	return newval;
}

function validReg(id, val){
	
	var reg;
	
	switch(id){
		case "passwordOri":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;
		
		case "password":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;
			
		case "passwordCheck":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;
		
		case "nickname":
			reg = /^[a-zA-Z0-9가-힣]{2,20}$/;		
		break;
		
	}
	return reg.test(val);
}

$(function(){
	firstNickname = $("#nickname").val();
	console.log("firstNickname : "+firstNickname)
	$("input[type='text'], input[type='password']").on("blur",function(){
		nicknameChk = false;
		var cid = this.id;
		var cv = groSpace($(this).val());
    	var checkValue = $(this).val(cv).val();
    	var regValue = validReg(cid, checkValue);
	
		switch(cid){
			case "passwordOri":
				$('.msg1').html('');
				passwordMatch(checkValue);
				break;
			
	        case "password":
	
	            if(!regValue){
	                $('.msg2').html('형식에 맞지 않는 비밀번호 입니다.');
	                password = false;
	            } else {        
	                $('.msg2').html('<b style="color:blue">사용가능한 비밀번호 입니다.</b>');
					password = true;
	            }
	            break;
	            
	        case "passwordCheck":
	
	            if(checkValue != $("#password").val()){
					passwordChk = false;
	                $('.msg3').html('비밀번호가 일치하지 않습니다.');
	            } else {
	              
	                $('.msg3').html('<b style="color:blue">비밀번호가 일치합니다.</b>');
					passwordChk = true;
	            }
	            break;
				
				
			case "nickname":
				console.log("nickname : "+checkValue);
				console.log(regValue);
				   if(!regValue){
					   $(".msg5").html('형식에 맞지 않는 닉네임입니다.');
				   }else{
					   if(firstNickname == checkValue){
						   $(".msg5").html('현재 사용 중인 닉네임입니다.');
					   }else{
						   nicknameCheck(checkValue);
					   }
				   }    	
	            break;
		}
	})
})

function nicknameCheck(nickname){
	$.ajax({
		type: "GET",
		url: "/user/check-nickname",
		data: {"nickname":nickname},
		dataType: "json",
		success:function(data){				
			if(data.success > 0){				
				$('.msg5').html('이미 사용 중인 닉네임 입니다.');					
			}else{
				$('.msg5').html('<b style="color:blue">사용가능한 닉네임입니다.</b>');
				nicknameChk = true;
				newNickname = nickname;
			}
		},
		error: function(e){
			console.log(e);
		}
	})
}

function saveInfo(){
	var username = $("#username").val();
	var nickname = newNickname;
	if(!nicknameChk){
		$("#nickname").focus();
	}else{
		var saveChk = confirm("변경된 회원정보를 변경하시겠습니까?");
		console.log("aa : "+saveChk)
		console.log("aa : "+nicknameChk)
		if(saveChk && nicknameChk){
			$.ajax({
				type : 'put',
				url : '/mypage/update',
				data : {'username': username, 'nickname':nickname},
				dataType : 'json',
				success : function(data){
					if(data == 1){
						alert("회원정보가 변경되었습니다.");
						location.href="/mypage";
					}else{
						alert("회원정보 변경에 실패하였습니다.");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		}else{
			$("#nickname").focus();
		}
	}
}

function passwordMatch(password){
	var user_num = $("#userNum").val();
	$.ajax({
		type : 'post',
		url : '/mypage/passwordMatch',
		data : {'user_num':user_num, 'password': password},
		dataType : 'json',
		success : function(data){
			if(data == 1){
				passwordOriChk = true;
			}else{
				passwordOriChk = false;
			}
		},
		error : function(e){
			console.log(e);
		}
		
	})
}

function pwReset(){
	if(password && passwordChk && passwordOriChk){
		var username = $("#username").val();
		var new_password = $("#password").val();
		$.ajax({
			type : 'post',
			url : '/user/pwReset',
			data : {'username':username, 'password':new_password},
			dataType : 'json',
			success : function(data){
				var msg = "비밀번호가 재설정 되었습니다."
				if(data == 1){
					$(".close").click();
					alert(msg);
					$("#passwordOri").val('');
					$("#password").val('');
					$("#passwordCheck").val('');
					$('.msg1').html('');
					$('.msg2').html('');
					$('.msg3').html('');
				}else{
					msg = "비밀번호 재설정에 실패하였습니다."
					alert(msg);
				}
			},
			error: function(e){
				console.log(e);
			}
		})
		
	}else if(!password){
		$("#password").focus();
	}else if(!passwordOriChk){
		$('.msg1').html('비밀번호가 일치하지 않습니다.');
		$("#passwordOri").focus();
	}
	else{
		$("#passwordCheck").focus();
	}
}

function imgUrl(input){
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function(e){
			document.getElementById('profilePreview').src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	}else{
		document.getElementById('profilePreview').src="";
	}
}

function delProfile(){
	var check = confirm("프로필 이미지를 삭제하시겠습니까?");
	
	if(check){
		var user_num = $("#user_num").val();

		$.ajax({
			type : 'post',
			url : '/mypage/delProfile',
			data : {'user_num':user_num},
			dataType : 'json',
			success : function(data){
				if(data>0){
					location.href ="/mypage"
				}
			},
			error: function(e){
				console.log(e);
			}
		})
		
	}
}
