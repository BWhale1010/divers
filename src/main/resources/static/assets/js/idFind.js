var allEmail = null;
var emailCheck = false;
var emailCode = null;
var emailCodeCheck = false;

function groSpace(val){
	var newval = val.replace(/\s/g,"");
		
	return newval;
}

function validReg(id, val){
	
	var reg;
	
	switch(id){
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
		emailCheck = false;
		emailCodeCheck = false;
		
		var cid = this.id;
	    
	    if(cid != "emailCheck"){
			var cv = groSpace($(this).val());
	    	var checkValue = $(this).val(cv).val();
	    	var regValue = validReg(cid, checkValue);
		}
		
		switch(cid){
			case "email1":
				emailCheck = false;
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
	console.log("이메일 체크 : "+email)
	$.ajax({
		type: "GET",
		url: "/user/check-email",
		data: {"email":email},
		dataType: "json",
		success:function(data){				
			if(data.success > 0){
		
				$('.msg4').html('인증번호 받기를 진행해주세요.');					
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
	
$("#email-idFind-btn").on("click", function(){
	emailCodeCheck = false;
	emailSend(allEmail);
})

function emailSend(email){
	var func = "idFind";
	if(emailCheck){
		$.ajax({
		tyle: "GET", 
		url:"/user/email",
		data:{'email':email, 'func': func},
		dataType: 'json',
		success:function(data){
			$('.msg5').html('인증번호를 입력해 주세요.');
			console.log(data);
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
		console.log("emailCodeCheck : "+emailCodeCheck);
		console.log("emailCheck : "+emailCheck);
		$('.msg4').html('');
	}else{
		$('.msg5').html('인증번호가 다릅니다.');
	}		
}


function idFind() {
    if (emailCheck && emailCodeCheck) {
        let f = document.createElement('form');
        f.setAttribute('method', 'post');
        f.setAttribute('action', '/user/idFind');

        // input 요소 생성
        let input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "email");
        input.setAttribute("value", allEmail);

        // input 요소를 폼에 추가
        f.appendChild(input);

        // 폼을 문서에 추가하고 제출
        document.body.appendChild(f);
        f.submit();
    }else if(!emailCheck){
		$("#email1").focus();
	}else{
		$("#emailCheck").focus();
	}
}

