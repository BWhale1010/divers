$('#join-btn').click(function(){
        if($('#joinCheck').is(':checked')){
            //console.log("체크박스가 체크되었습니다.");
            location.href = "/join"
        }else{
            // 체크박스가 해제되었을 때 실행할 기능
            //console.log("체크박스가 해제되었습니다.");
            alert("회원가입 동의 체크를 해주세요.")
        }
});