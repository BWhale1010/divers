var postNum = $("#post_num").val();

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
			commentDraw(data.list);
			
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

function commentDraw(list){
	var content = "";
	
	list.forEach(function(item){
		content += '<div class="comment d-flex mb-4"><div class="flex-shrink-0">'
		+'<div class="avatar avatar-sm rounded-circle"><img class="avatar-img" src="/assets/img/person-5.jpg" alt="">'
		+'</div></div> <div class="flex-grow-1 ms-2 ms-sm-3"><div class="comment-meta d-flex align-items-baseline">'
		+'<h6 class="me-2">'+item.nickname+'</h6><span class="text-muted">'+item.comment_date+'</span></div>'
		+'<div class="comment-body">'+item.comment+'</div>'
		+'<div><div class="card-body text-end"><a href="#"><img src="/assets/img/thumb.png" style="width: 3%"></a>'
		+item.recommend+'<span style="margin: 0 10px;">|</span> <a href="#">신고하기</a></div></div>'
		+'</div></div>'
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


