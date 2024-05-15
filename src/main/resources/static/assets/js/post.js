var postNum = $("#post_num").val();
var user_num = $("#loginId").val();
commentList(1, postNum);

function commentList(page, postNum){
	console.log("postNum : "+postNum);
	$.ajax({
		type : 'post',
		url : '/comment/list',
		data : {'page' : page, 'post_num' : postNum},
		dataType : 'json',
		success : function(data){
			console.log(data)
			commentDraw(data.list, data.role_num);
			
				$('#pagination').twbsPagination({
				startPage : 1,
				totalPages: data.total,
				visiblePages: 5,
				onPageClick: function(e,page){
					console.log(e);
					commentList(page, postNum);
				}
			});			
		},
		error : function(e){
			console.log(e)
		}
	})
}

function commentDraw(list, role_num){
	var content = "";
	var loginId = $("#loginId").val();
	console.log("loginId : "+loginId);

	list.forEach(function(item){
   		content +=
        '<hr><div class="comment d-flex mb-4"><div class="flex-shrink-0">'
        + '<div class="avatar avatar-sm rounded-circle"><img class="avatar-img" src="' 
        + (item.new_filename ? '/photo/'+item.new_filename+'' : '/assets/img/profile.png') + '" alt=""></div></div>'
        + '<div class="flex-grow-1 ms-2 ms-sm-3"><div class="comment-meta d-flex align-items-baseline">'
        + '<h6 class="me-2">' + item.nickname + '</h6><span class="text-muted">' + item.comment_date + '</span></div>'
        + '<div class="comment-body">' + item.comment + '</div>'
        + '<div><div class="card-body text-end"><a id="'+item.comment_num+'" onclick="commentRecommend('+item.comment_num+')"><img src="'
        + (item.is_recommended == 1 ? '/assets/img/thumb_color.png' : '/assets/img/thumb.png') + '" style="width: 3%"></a>'
        + item.recommend + '<span style="margin: 0 10px;">|</span>';
        
    if (loginId == item.user_num) {
        	content += 
            '<div class="dropdown"><a class="dropbtn">• • •</a>'
            + '<div class="dropdown-content"><a id="'+item.comment_num+'" onclick="commentEdit(this.id, \''+item.comment+'\', '+item.user_num+')">수정하기</a>'
            + '<a id="'+item.comment_num+'" onclick="commentDelete(this.id, '+item.user_num+')">삭제하기</a></div>'
            + '</div></div></div></div></div>';
    } else {
		if(role_num == 3 || role_num == null){
			content +=
            '<a href="#" onclick="#">신고하기</a></div></div></div></div>';
		}else if(role_num == 1 || role_num == 2){
			content +=
            '<a onclick="commentBlind('+item.comment_num+','+item.post_num+');">블라인드</a></div></div></div></div>';
		}
        	
    }
	})
	
	$("#commentList").empty();
	$("#commentList").append(content);
}



function deletePost(postNum, small_category_num, user_num){
	
	var result = confirm("해당 글을 삭제하시겠습니까?");
	
	if(result){
			$.ajax({
		type : 'put',
		url : '/board/delete/'+postNum,
		dataType : 'json',
		data : {'user_num' : user_num},
		success : function(){
			location.href="/board/list/"+small_category_num;
		},
		error: function(e){
			console.log(e);
		}
	})
	}
}

$(function(){
		$("#comment-btn").on("click", function(){
			var comment = $("#comment").val();
			var loginId = $("#loginId").val();
			var post_num = $("#post_num").val();		
			
			if (!loginId) {
			    var loginCheck = confirm("로그인을 하시겠습니까?");
				if (loginCheck) {
				    var name = "visitedPage";
				    var currentPageUrl = window.location.pathname;			    	    		 				    
				    
				    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(currentPageUrl) + "; path=/";
				    
				    location.href = "/user/login";
				}

			}
			else if(comment === ""){
				alert("내용을 작성해 주세요.");
			} else {
			    let params = {
			        comment: comment,
			        post_num: post_num,
			        user_num: loginId
			    };
			    $.ajax({
			        type: "post",
			        url: "/comment/write",
			        data: params,
			        dataType: "json",
			        success: function (data) {
			            console.log(data);
			            location.href = "/board/detail/" + post_num;
			        },
			        error: function (e) {
			            console.log(e);
			        }
			    });
			}
		})
})

function commentDelete(comment_num, user_num){
	console.log("comment_num : "+comment_num);
	console.log("user_num : "+user_num);
	var deleteCheck = confirm("해당 댓글을 삭제하시겠습니까?")
	
	if(deleteCheck){
		
		$.ajax({
			type: 'put',
			url : '/comment/delete/'+comment_num,
			data : {"user_num":user_num},
			dataType : 'json',
			success : function(data){
				if(data>0){
					location.href="/board/detail/"+postNum;
				}
						
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}

function commentEdit(comment_num, comment, user_num){

    $("#cancel-btn").show();
    $("#commentEdit-btn").show();
    $("#comment-btn").css("display", "none");
    $("#comment").val(comment);
    $("#comment").focus();
    
    $("#cancel-btn").click(function(){
        $(this).hide();
		$("#commentEdit-btn").hide();
		$("#comment-btn").show();
        $("#comment").val('');        
    });
    
    $("#commentEdit-btn").click(function(){
		console.log("comment_num : "+ comment_num);
    	console.log("comment : "+ comment);
    	console.log("user_num : "+ user_num);
    	var newComment = $("#comment").val();
    	$.ajax({
			type : 'put',
			url : '/comment/edit/'+comment_num,
			data : {'comment':newComment, 'user_num':user_num},
			dataType : 'json',
			success: function(data){
				if(data>0){
					location.href = "/board/detail/"+postNum;
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	})
}

function postRecommend(){
	var loginId = $("#loginId").val();
	
	console.log("loginId : "+loginId);
	if(loginId == ""){
		var loginCheck = confirm("로그인을 하시겠습니까?");
				if (loginCheck) {
				    var name = "visitedPage";
				    var currentPageUrl = window.location.pathname;			    	    		 				    
				    
				    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(currentPageUrl) + "; path=/";
				    
				    location.href = "/user/login";
				}
	}else{
		$.ajax({
			type : 'put',
			url : "/board/thumb/"+postNum,
			data : {'user_num': loginId},
			dataType : 'json',
			success: function(data){
				if(data>0){
					location.href = "/board/detail/"+postNum;
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	}
}

function commentRecommend(comment_num){
	console.log("comment_num : "+comment_num);
	console.log("user_num : "+user_num);
	if(user_num == ""){
		var loginChk = confirm("로그인을 하시겠습니까?");
		if(loginChk){
		    var name = "visitedPage";
		    var currentPageUrl = window.location.pathname;			    	    		 				    
		    
		    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(currentPageUrl) + "; path=/";
		    
		    location.href = "/user/login";			
		}
	}else{
		$.ajax({
			type : 'post',
			url : '/comment/thumb/'+comment_num,
			dataType : 'json',
			data : {"user_num":user_num},
			success :function(data){
				if(data>0){
					location.href = "/board/detail/"+postNum;
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	}
}

function postBlind(post_num){
	var check = confirm("이 게시글을 블라인드 처리하시겠습니까?");
	if(check){
		$.ajax({
			type : 'post',
			url : '/manage/postBlind',
			data : {'post_num':post_num},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					location.href = "/board/detail/"+post_num;	
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}

function commentBlind(comment_num, post_num){
	var check = confirm("이 댓글을 블라인드 처리하시겠습니까?");
	
	if(check){
		$.ajax({
			type: 'post',
			url : '/manage/commentBlind',
			data :{'comment_num':comment_num},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					location.href = "/board/detail/"+post_num;	
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}

function postClear(post_num){
	var check = confirm("이 게시글의 블라인드를 해제하시겠습니까?");
	
	if(check){
		$.ajax({
			type : 'post',
			url : '/manage/postClear',
			data : {'post_num':post_num},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					location.href = "/board/detail/"+post_num;
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}

function reportWriteModal(post_num){
	var loginId = $("#loginId").val();
	var check = confirm("해당 게시글을 신고하시겠습니까?");
	if(check){
		if(!loginId){
		var loginCheck = confirm("로그인을 하시겠습니까?");
		if (loginCheck) {
		    var name = "visitedPage";
		    var currentPageUrl = window.location.pathname;			    	    		 				    
		    
		    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(currentPageUrl) + "; path=/";
		    
		    location.href = "/user/login";
		}
	}else{
		$.ajax({
			type : 'post',
			url : '/manage/reportPostCheck',
			data : {'post_num':post_num},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					alert("이미 신고된 게시글입니다.");
				}else{
					$("#reportWriteModal").modal("show")
					$("#modalPost_num").val(post_num);
					$("#loginId").val(loginId);
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
	}
}

$("#report-btn").on("click",function(){
	var post_num = $("#modalPost_num").val();
	var user_num = $("#loginId").val();
	var reportSelect = $("#reportSelect").val();
	var reportDetail = $("#reportDetail").val();
	
	let param = {
		'post_num':post_num,
		'user_num':user_num,
		'sort_num':reportSelect,
		'report_detail':reportDetail,
		
	} 
	
	if(reportSelect == "init" ){
		alert("신고 분류를 선택해 주세요");
		
	}else if(reportDetail == ""){
		alert("신고 상세이유를 작성해주세요.")
	}else{
		$.ajax({
			type : 'post',
			url : '/manage/reportWrite',
			data : param,
			dataType : 'json',
			success : function(data){
				alert("해당 게시글을 신고하였습니다.");
				if(data == 1){
					location.href = "/board/detail/"+post_num;
				}
			},
			error : function(e){
				console.log(e);
			}
			
		})
	}
})