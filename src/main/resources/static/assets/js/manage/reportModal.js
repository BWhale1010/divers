$('#reportModal').on('show.bs.modal', function (event) {
    var post_num = $(event.relatedTarget).data('post_num');
    var comment_num = $(event.relatedTarget).data('comment_num');

    if(post_num === undefined){
		commentInfo(comment_num);
	}else{
		modalInfo(post_num);
	}

});

function commentInfo(comment_num){
	
	$.ajax({
		type : 'post',
		url : '/manage/commentInfo',
		data : {'comment_num':comment_num},
		dataType : 'json', 
		success : function(data){
			comModalDraw(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function comModalDraw(report){
	var content = '';
	
	report.forEach(function(report){
		content += '<div class="swiper-slide"><div class="report-info-container">'+
		'<div class="post-meta"><span class="date">'+report.small_class_name+' </span><span class="mx-1">•</span><span>'+report.comment_date+'</span>'+
		' | <span class="date">'+report.nickname+'</span><span class="mx-1">•</span>'+
		' | <span class="date">신고수 '+report.reportCount+'</span><span class="mx-1">•</span><span>'+report.comment_state_name+'</span></div>'+
		'<h1 class="mb-3">신고 내용</h1><p>'+report.report_detail+'</p>'+
		'</div><input type = "hidden" value="'+report.user_num+'" id="reportUser_num"><input type = "hidden" value="'+report.comment_num+'" id="reportComment_num"></div>';
	})
              				
    $("#reportInfo").empty().append(content);
}

function postBlind(){

	var post_num = $("#reportPost_num").val();
	var comment_num = $("#reportComment_num").val();

	if(comment_num === undefined){
		var check = confirm("이 게시글을 블라인드 처리하시겠습니까?");
		if(check){
		$.ajax({
		type : 'post',
		url : '/manage/postBlind',
		data : {'post_num':post_num},
		dataType : 'json',
		success : function(data){
			if(data == 1){
				alert("이 게시글을 블라인드 처리하였습니다.");
			}
		},
		error : function(e){
			console.log(e)
		}
	})
	}
	}else{
		var check1 = confirm("이 댓글을 블라인드 처리하시겠습니까?");
		if(check1){
		$.ajax({
		type : 'post',
		url : '/manage/commentBlind',
		data : {'comment_num':comment_num},
		dataType : 'json',
		success : function(data){
			if(data == 1){
				alert("이 댓글을 블라인드 처리하였습니다.");
			}
		},
		error : function(e){
			console.log(e)
		}
	})
	}
	}
}


function modalInfo(post_num){
	
	$.ajax({
		type : 'post',
		url : '/manage/reportInfo',
		data : {'post_num':post_num},
		dataType : 'json', 
		success : function(data){
			reportDraw(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function reportDraw(report){
	var content = '';
	
	report.forEach(function(report){
		content += '<div class="swiper-slide"><div class="report-info-container">'+
		'<div class="post-meta"><span class="date">'+report.small_class_name+' </span><span class="mx-1">•</span><span>'+report.board_date+'</span>'+
		' | <span class="date">'+report.nickname+'</span><span class="mx-1">•</span> <span>조회수 '+report.count+'</span><span class="mx-1">•</span><span>추천수 '+report.recommend+'</span>'+
		' | <span class="date">신고수 '+report.reportCount+'</span><span class="mx-1">•</span><span>'+report.post_state_name+'</span></div>'+
		'<h1 class="mb-3">신고 내용</h1><p>'+report.report_detail+'</p>'+
		'</div><input type = "hidden" value="'+report.user_num+'" id="reportUser_num"><input type = "hidden" value="'+report.post_num+'" id="reportPost_num"></div>';
	})
              				
    $("#reportInfo").empty().append(content);
}

function suspUser(){
	var user_num = $("#reportUser_num").val();
	var check = confirm("해당 유저를 일시정지 처리하시겠습니까?");
	
	if(check){
		$.ajax({
			type : 'post',
			url : '/manage/suspUser',
			data : {'user_num':user_num},
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
}
