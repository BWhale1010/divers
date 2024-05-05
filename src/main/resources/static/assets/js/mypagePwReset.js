var passwordChk = false;
var password = false;

function groSpace(val){
	var newval = val.replace(/\s/g,"");
		
	return newval;
}
	
function validReg(id, val){
		
	var reg;
		
	switch(id){
		case "password":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;
			
		case "passwordCheck":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;

		// 이메일 RFC 5321 및 RFC 5322의 표준
		}
		
		return reg.test(val);
}
$(function(){
	
	$("input[type='password']").on("input", function(){

		var cid = this.id;
	    
		var cv = groSpace($(this).val());
    	var checkValue = $(this).val(cv).val();
    	var regValue = validReg(cid, checkValue);

		
	switch(cid){
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
	}
	})
})

function pwReset(){
	if(password && passwordChk){
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
					location.href="/?msg="+msg;
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
	}else{
		$("#passwordCheck").focus();
	}
}