var allEmail = null;
var emailCheck = false;
var emailCode = null;
var emailCodeCheck = false;
var idCheck = false;

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
		
		case "email1":
			reg = /^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*$/;
		break;
		
		case "emailAddInput":
			reg = /^(?:(?:[a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$/;
		break;
		
		case "fullEmail":
			reg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;	
		break;
		
	}
	return reg.test(val);
}

$(function(){
	
	$("input[type='text']").on("input", function(){
		
		
		
		var cid = this.id;
	    
	    if(cid != "emailCheck"){
			var cv = groSpace($(this).val());
	    	var checkValue = $(this).val(cv).val();
	    	var regValue = validReg(cid, checkValue);
		}
		
		switch(cid){
	        case "username":
				idCheck = false;
	            if(!regValue){					
	                $('.msg1').html('형식에 맞지 않는 아이디 입니다.');      
	            } else {               
	                if(emailCodeCheck){
						$('.msg1').html('<b style="color:blue">비밀번호 재설정을 진행해주세요.</b>');
					}else{
						$('.msg1').html('<b style="color:blue">이메일을 입력해 주세요.</b>');
					}
	                idCheck = true;
	            }
	            break;
			
			case "email1":
				emailCheck = false;
				emailCodeCheck = false;
				if(!regValue) {
		        $('.msg4').html('이메일을 확인해 주세요.');
		    }else {
		        if ($("#email2").val() == "free" && $("#emailAddInput").val() === "") {

		            $('.msg4').html('이메일을 작성해 주세요.');
		        } else if ($("#email2").val() == "free") {
				
		            addEmailCheck($("#emailAddInput").val());
		        } else if ($("#email2").val() == "init") {
	
		            $('.msg4').html('이메일을 선택해 주세요.');
		        }else{
					emailConcat($("#email2").val());
				}
		    }
		    break;
		    
		   case "emailCheck":
			   emailCodeCheck = false;
				emailAuth(emailCode);            	
           break;
		}
	})
})

$(function(){
	$("#email2").on("change", function(){
		emailCheck = false;
		emailCodeCheck = false;
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
		emailConcat(selectedValue);
    }
});

    $(document).on("blur", "#emailAddInput", function(){
        var emailAddVal = $(this).val();
        addEmailCheck(emailAddVal);
    });
})

function emailConcat(domain){
	var local = $("#email1").val();

	var fullEmail = local +"@"+ domain;
		
    var cid = "fullEmail"
    var cv = groSpace(fullEmail);
    var checkValue = cv;
    var regValue =  validReg(cid, checkValue);
   
    if(regValue){
		allEmail = checkValue;
	}
	emailCheckFunc(checkValue);
	
}

function addEmailCheck(emailVal){
	    if(emailVal != ""){
        var cid = $("#emailAddInput").attr("id");
        var cv = groSpace(emailVal);
        var checkValue = cv;
        var regValue =  validReg(cid, checkValue);
        
        if(regValue){
            emailConcat($("#emailAddInput").val());             
        } else {

            $('.msg4').html('올바른 이메일 주소가 아닙니다.');
        }
    } else {

        $('.msg4').html('나머지 이메일을 입력해주세요.');
    }	
}

function emailCheckFunc(email){
	$.ajax({
		type: "GET",
		url: "/user/check-email",
		data: {"email":email},
		dataType: "json",
		success:function(data){				
			if(data.success > 0){
				$('.msg4').html('인증번호 받기를 진행해주세요.');
				$('.msg1').html('');		
				emailCheck = true;
				
			}else{
				$('.msg4').html('존재하지 않는 이메일입니다.');
				emailCheck = false;
			}
		},
		error: function(e){
			console.log(e);
		}
	})
}

$("#email-pwReset-btn").on("click", function(){
	emailCodeCheck = false;
	$('.msg4').html('');
	emailSend(allEmail);
})

function emailSend(email){
	var func = "pwReset";
	if(emailCheck){
		$.ajax({
		tyle: "GET", 
		url:"/user/email",
		data:{'email':email, 'func': func},
		dataType: 'json',
		success:function(data){
			$('.msg5').html('인증번호를 입력해 주세요.');
			emailCode = data;
		},
		error: function(e){
			console.log(e);
		}
	})
			
	}else{
		$("#email1").focus();
	}
}

function emailAuth(code){
	if(code == $("#emailCheck").val()){
		$('.msg5').html('<b style="color:blue">이메일 인증이 완료되었습니다.</b>');
		emailCodeCheck = true;
		emailCheck = true;

		$('.msg4').html('');
	}else{
		$('.msg5').html('인증번호가 다릅니다.');
	}		
}

function pwReset(){
	if(emailCodeCheck && emailCheck && idCheck){
		var username = $("#username").val()
		var email = allEmail;

		$.ajax({
			type : 'post',
			url : '/user/pwResetCheck',
			data : {'username':username, 'email': email},
			dataType : 'json',
			success : function(data){
				if(data == 1){
			        let f = document.createElement('form');
			        f.setAttribute('method', 'post');
			        f.setAttribute('action', '/user/pwResetResult');
			
			        let input1 = document.createElement("input");
			        input1.setAttribute("type", "hidden");
			        input1.setAttribute("name", "email");
			        input1.setAttribute("value", email);
			        
			        let input2 = document.createElement("input");
			        input2.setAttribute("type", "hidden");
			        input2.setAttribute("name", "username");
			        input2.setAttribute("value", username);
			
			        f.appendChild(input1);
			        f.appendChild(input2);
			
			        document.body.appendChild(f);
			        f.submit();
				}else{
					alert("아이디와 이메일이 일치하는 계정이 없습니다.");
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}else if(!idCheck){
		$("#username").focus();
	}else if(!emailCheck){
		$("#email1").focus();
	}else{
		$("#emailCheck").focus();
	}
	

}