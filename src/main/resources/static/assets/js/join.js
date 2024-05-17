
$('#joinCheck-btn').click(function(){
        if($('#joinCheck').is(':checked')){
            location.href = "/user/join"
        }else{
            alert("회원가입 동의 체크를 해주세요.")
        }
});


$(function(){
	$("#join-btn").on("click", function(){
		var allCheck = true;
		
		for(var key in index){
			if(index[key] == false){
				var selector = "#"+key;
				var element = $(selector);
				$(element).focus();
				allCheck = false;
				break;
			}
		}

	if(allCheck){
					let params ={
			'username' : $("#username").val(),
			'password' : $("#password").val(),
			'email' : index.allEmail,
			'nickname' : $("#nickname").val()
			}
		
			console.log(params.username);
			console.log(params.password);
			console.log(params.email);
			console.log(params.username);
			
			$.ajax({			
			type : "post",
			url : "/user/join",
			data : params,
			dataType : "json",
			success: function(data){				
				alert(data.msg);
				
				if(data.state == "success"){
					location.href = "/";
				}
								
			},
			error: function(e){
				alert(e.msg);
				console.log(e.msg);
			}
		})
	}



})
})



function groSpace(val){
	var newval = val.replace(/\s/g,"");
		
	return newval;
}
	
function validReg(id, val){
		
	var reg;
		
	switch(id){
		case "username":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{6,20}$/;
		break;
				
		case "password":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;
			
		case "passwordCheck":
			reg = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+{};:,<.>]{6,20}$/;
		break;
			
		case "email1":
			reg = /^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*$/;
		break;
		
		case "emailAddInput":
			reg = /^(?:(?:[a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$/;
		break;
		
		case "fullEmail":
			reg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;	
		break;
		
		case "nickname":
			reg = /^[a-zA-Z0-9가-힣]{2,20}$/;		
		break;
		
		
		// 이메일 RFC 5321 및 RFC 5322의 표준
		}
		
		return reg.test(val);
}


let index = {
	
	allEmail: null,
	emailCode: null,
	username:false,
	password:false,
	passwordCheck:false,
	email1:false,
	emailCheck:false,
	nickname:false,
	
	inputCheck:function(){
	
	$(function(){

	$("input[type='text'], input[type='password']").on("focusout", function() {
		

	var cid = this.id;
    
    if(cid != "emailCheck"){
		var cv = groSpace($(this).val());
    	var checkValue = $(this).val(cv).val();
    	var regValue = validReg(cid, checkValue);
	}
    
    switch(cid){
        case "username":
			index.username = false;
            if(!regValue){
				
                $('.msg1').html('형식에 맞지 않는 아이디 입니다.');
            } else {
                index.usernameCheck(checkValue);
            }
            break;
            
        case "password":
                index.password = false;
            if(!regValue){
            

                $('.msg2').html('형식에 맞지 않는 비밀번호 입니다.');
            } else {        
                $('.msg2').html('<b style="color:blue">사용가능한 비밀번호 입니다.</b>');
                index.password = true;
            }
            break;
            
        case "passwordCheck":
			index.passwordCheck = false;
            if(checkValue != $("#password").val()){

                $('.msg3').html('비밀번호가 일치하지 않습니다.');
            } else {
              
                $('.msg3').html('<b style="color:blue">비밀번호가 일치합니다.</b>');
                index.passwordCheck = true;
            }
            break;
            
		case "email1":
			index.email1 = false;
			index.emailCheck = false;   
		    if (!regValue) {
		        $('.msg4').html('이메일을 확인해 주세요.');
		    } else {
		        if ($("#email2").val() == "free" && $("#emailAddInput").val() === "") {

		            $('.msg4').html('이메일을 작성해 주세요.');
		        } else if ($("#email2").val() == "free") {
				
		            index.addEmailCheck($("#emailAddInput").val());
		        } else if ($("#email2").val() == "init") {
	
		            $('.msg4').html('이메일을 선택해 주세요.');
		        } else {
		            index.emailConcat($("#email2").val());    
		        }
		    }
		    break;
		    
		 case "emailCheck":
			  index.emailCheck = false;
			 index.emailAuth(index.emailCode);            	
            break;
            
        case "nickname":
			 index.nickname = false;
			   if(!regValue){
				   $(".msg6").html('형식에 맞지 않는 닉네임입니다.');
			   }else{
				   index.nicknameCheck($("#nickname").val());
			   }    	
            break;
    }
    
});


})
	
$(document).ready(function(){

    $("#email2").on("change", function(){
		index.email1 = false;	
		index.emailCheck = false;
        var selectedValue = $(this).val();
        
        if (selectedValue === "free") {
            var inputGroup = $("<div>").addClass("form-group align-horizontal");
            var emailSuffixInput = $("<input>").attr({
                type: "text",
                id: "emailAddInput",
                class: "form-control",
                placeholder: "이메일 주소 뒷부분을 입력해주세요."
            });
            inputGroup.append(emailSuffixInput);
            $("#customInput").empty().append(inputGroup);
        } else {
            $("#customInput").empty();
        }
        
        if(selectedValue == "init"){

            $('.msg4').html('이메일 주소를 선택해주세요.');
        } else if(selectedValue == "free"){

            $('.msg4').html('나머지 이메일을 입력해주세요.');
        } else {
			index.emailConcat(selectedValue);
            //$('.msg4').html('<b style="color:blue">이메일 인증을 진행해주세요.</b>');
        }
    });

    // blur 이벤트 핸들러를 document로 위임하여 동적으로 생성된 요소에 대한 이벤트도 캐치
    $(document).on("blur", "#emailAddInput", function(){
		index.email1 = false;	
		index.emailCheck = false;
        var emailAddVal = $(this).val();
        index.addEmailCheck(emailAddVal);
    });

	$("#email-btn").on("click",function(){
		console.log(index.allEmail);
		index.emailSend(index.allEmail);
	})


});

}
, addEmailCheck:function(emailVal){
				   
	        if(emailVal != ""){
            var cid = $("#emailAddInput").attr("id");
            var cv = groSpace(emailVal);
            var checkValue = cv;
            var regValue =  validReg(cid, checkValue);
            
            if(regValue){
                index.emailConcat($("#emailAddInput").val());             
            } else {

                $('.msg4').html('올바른 이메일 주소가 아닙니다.');
            }
        } else {

            $('.msg4').html('나머지 이메일을 입력해주세요.');
        }
}     

	,usernameCheck:function(username){
		
		$.ajax({
			type: "GET",
			url: "/user/check-username",
			data: {"username":username},
			dataType: "json",
			success:function(data){
				
				if(data.success > 0){

					$('.msg1').html('이미 사용 중인 아이디 입니다.');
					
				}else{
					$('.msg1').html('<b style="color:blue">사용가능한 아이디 입니다.</b>');
					index.username = true;
					
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	}
	,emailConcat:function(domain){
		var local = $("#email1").val();

		
		var fullEmail = local +"@"+ domain;
		
		    var cid = "fullEmail"
            var cv = groSpace(fullEmail);
            var checkValue = cv;
            var regValue =  validReg(cid, checkValue);
           
            if(regValue){
				index.allEmail = checkValue;
			}
			index.emailCheckFunc(checkValue);
	}
	,emailCheckFunc:function(email){		
		$.ajax({
			type: "GET",
			url: "/user/check-email",
			data: {"email":email},
			dataType: "json",
			success:function(data){				
				if(data.success > 0){
			
					$('.msg4').html('이미 사용 중인 이메일 입니다.');					
					
				}else{
					$('.msg4').html('이메일 인증을 진행해주세요.');
						index.email1 = true;		
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	}
	,emailSend:function(email){
		var func  = "join"
		if(index.email1){
			$.ajax({
			tyle: "GET", 
			url:"/user/email",
			data:{'email':email, 'func': func},
			dataType: 'json',
			success:function(data){
				$('.msg5').html('인증번호를 입력해 주세요.');
				console.log(data);
				index.emailCode = data;
			},
			error: function(e){
				console.log(e);
			}
		})
		}else{
			$("#email1").focus();
		}

	}
	,emailAuth:function(code){	
		if(code == $("#emailCheck").val()){
			$('.msg5').html('<b style="color:blue">이메일 인증이 완료되었습니다.</b>');
			index.emailCheck = true;
			$('.msg4').html('');
		}else{
			$('.msg5').html('인증번호가 다릅니다.');
		}							
	}
	,nicknameCheck:function(nickname){		
		$.ajax({
			type: "GET",
			url: "/user/check-nickname",
			data: {"nickname":nickname},
			dataType: "json",
			success:function(data){				
				if(data.success > 0){				

					$('.msg6').html('이미 사용 중인 닉네임 입니다.');					

				}else{
					$('.msg6').html('<b style="color:blue">사용가능한 닉네임입니다.</b>');
					index.nickname = true;
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	}
	
	
	
}       

index.inputCheck();


