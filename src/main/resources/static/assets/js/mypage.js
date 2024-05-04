var firstNickname = null;
var newNickname = null;
var nicknameChk= false;

function groSpace(val){
	var newval = val.replace(/\s/g,"");
		
	return newval;
}

function validReg(id, val){
	
	var reg;
	
	switch(id){
		case "nickname":
			reg = /^[a-zA-Z0-9가-힣]{2,10}$/;		
		break;
		
	}
	return reg.test(val);
}

$(function(){
	firstNickname = $("#nickname").val();
	console.log("firstNickname : "+firstNickname)
	$("input[type='text']").on("input", function(){
		nicknameChk = false;
		var cid = this.id;
		var cv = groSpace($(this).val());
    	var checkValue = $(this).val(cv).val();
    	var regValue = validReg(cid, checkValue);
	
		switch(cid){
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

$("#save-btn").on("click", function(){
	var username = $("#username").val();
	var nickname = newNickname;
	var saveChk = confirm("변경된 회원정보를 변경하시겠습니까?");
	
	if(saveChk && nicknameChk){
		$.ajax({
			type : 'put',
			url : '/mypage/update',
			data : {'username': username, 'nickname':nickname},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					alert("회원정보가 변경되었습니다.")
					location.href="/mypage"
				}else{
					alert("회원정보 변경에 실패하였습니다.")
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	}else{
		$("#nickname").focus();
	}
})

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