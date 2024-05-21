var total = 5;
var sort = "";
var direction = "";
var page = 1;
var search_word = "";
var commentSearch_word = "";
var group = "";
var state = "all";

listAdd(1, search_word, sort, direction, state);

$("#search-btn").on("click", function(){

    var word = $("#search-word").val();
    
    if(group == "" || group == "board"){
		search_word = word;
		listAdd(1, search_word, sort, direction, state);	
	}else{
		commentSearch_word = word;
		commentList(1, commentSearch_word, sort, direction, state);
	}
});

function listAdd(page, search_word, sort, direction, state){
	
	$.ajax({
		type : 'post',
		url : '/manage/boardList',
		data : {'page' : page,'search_word':search_word, 'sort':sort, 'direction':direction, 'state':state},
		dataType : 'json',
		success : function(data){
			if(data.list.length == 0 && search_word != ''){
				alert("해당 검색어로 검색된 게시글이 없습니다.");
				search_word = "";
				sort = "";
				direction = "";
				state = "all";
				listAdd(1, search_word, sort, direction, state);
			}
			
			thumbnail(data.list); 
			
				if(total != data.total){
					total = data.total
					 $('#pagination').twbsPagination('destroy');
				}
				
				$('#pagination').twbsPagination({
					startPage : 1,
					totalPages: total,
					visiblePages: 5,
					onPageClick: function(e,page){
						console.log(e);
						listAdd(page, search_word, sort, direction, state);
					}
				});
		},
		error : function(e){
			console.log(e);
		}
	})
	
}

function thumbnail(list) {
	var complet = 0;
	
for (let i = 0; i < list.length; i++) {

        $.ajax({
            type: 'post',
            url: '/board/thumbnail/' + list[i].post_num,
            dataType: 'json',
            success: function(data) {
                list[i].thumbnail = data.thumbnail;
                list[i].content = data.text;
                complet++;
                
                if(complet === list.length){
					listDraw(list);
				}
            },
            error: function(e) {
                console.log(e);
                complet++;
                
                if(complet === list.length){
					listDraw(list);
				}
            }
        });
	}
}

function listDraw(list){
	var content ="";
	var user_num = $("#user_num").val();
	
	if(user_num == ""){
		user_num = 0;
	}
	list.forEach(function(item){
		
		var thumbnailSrc = item.thumbnail === 'basic' ? '/assets/img/divers_thumbnail.png' : 'data:image/jpeg;base64,' + item.thumbnail;
		var contentText = item.content === '' ? '(이미지만 작성된 글입니다)' : item.content;
		var profileImg = item.new_filename === null ? '/assets/img/profile.png' : "/photo/" + item.new_filename;

	   content +=
        '<div class="d-md-flex post-entry-2 half" id="' + item.post_num + '"><a href="/board/detail/' + item.post_num + '" class="me-4 thumbnail">' +
        '<img src="' + thumbnailSrc + '" class="img-fluid"></a><div>' +
        '<div class="post-meta"><span class="date">' + item.small_class_name + '</span> <span class="mx-1">•</span>' +
        '<span>' + item.board_date + '</span></div><h3><a href="/board/detail/' + item.post_num + '">' + item.title + '</a></h3><p>' + contentText + '</p>' +
        '<div class="d-flex align-items-center author"><div class="photo"><img src="'+profileImg+'" alt="" class="img-fluid"></div>' +
        '<div class="name"><h3 class="m-0 p-0">' + item.nickname + '</h3></div></div><div class="post-meta">' +
        '<span class="date">조회수 ' + item.count + '</span> <span class="mx-1">•</span> <span>추천수 ' + item.recommend + '</span> <span class="mx-1">•</span> <span>신고수 ' + item.reportCount + '</span></div>'+
        '<div class="post-meta"><span class="date">블라인드 여부 :  ' + item.count + '</span></div>'+
        '</div></div>';

	});

	
	$("#boardList").empty();
	$("#boardList").append(content);
	
}

var sortDirections = {
    join: 'asc',
    report: 'asc',
    suspend: 'asc'
};

function sortBoard(sortBy) {
    var sortSpan = $('#' + sortBy + 'Sort');
    var sortDirection = sortDirections[sortBy];

    if (sortDirection === 'asc') {
        sortSpan.text('↓');
        sortDirections[sortBy] = 'desc';
    } else {
        sortSpan.text('↑');
        sortDirections[sortBy] = 'asc';
    }
    $('#pagination').twbsPagination('destroy');
    if(group == "" || group == "board"){
		listAdd(page, search_word, sortBy, sortDirections[sortBy], state);	
	}else{
		commentList(1, commentSearch_word, sortBy, sortDirections[sortBy], state);
	}
}

function toggle(category){
	group = category
	
	var toggleBoard = document.getElementById('toggleBoard');
    var toggleComment = document.getElementById('toggleComment');
	
	if(category == 'board'){
		toggleBoard.classList.add('active');
		toggleComment.classList.remove('active');
		$("#toggleBoard").css("color", "blue");
		$("#toggleComment").css("color", "");
		
		 listAdd(1, search_word, sort, direction, state);
		
	}else{
		toggleBoard.classList.remove('active');
		toggleComment.classList.add('active');
		$("#toggleBoard").css("color", "");
		$("#toggleComment").css("color", "blue");
		
		commentList(1, commentSearch_word, sort, direction, state);
		
	}
}

function commentList(page, commentSearch_word, sort, direction, state){
	
	$.ajax({
		type : 'post',
		url : '/manage/commentList',
		data : {'page':page, 'search_word':commentSearch_word, 'sort':sort, 'direction':direction, 'state':state},
		dataType : 'json',
		success : function(data){
			if(data.list.length == 0 && commentSearch_word != ''){
				alert("해당 검색어로 검색된 게시글이 없습니다.");
				commentSearch_word = "";
				sort = "";
				direction = "";
				state = "all";
				commentList(1, commentSearch_word, sort, direction, state);
				return;
			}else{
				commentDraw(data.list);
			
				if(total != data.total){
					total = data.total
					 $('#pagination').twbsPagination('destroy');
				}
				
				$('#pagination').twbsPagination({
					startPage : 1,
					totalPages: total,
					visiblePages: 5,
					onPageClick: function(e,page){
						console.log(e);
						commentList(page, commentSearch_word, sort, direction, state);
					}
				});
			}
		},
		error : function(e){
			console.log(e);
		}
	})
}

function commentDraw(list){
	var content = ''; 
	
	list.forEach(function(item){
		var profileImg = item.new_filename === null ? '/assets/img/profile.png' : "/photo/" + item.new_filename;
		var commentClear = item.comment_state_num == 2 ? '<span style="margin: 0 10px;">|</span><a onclick="commentClear('+item.comment_num+');">블라인드 해제</a>' : '';
		var reportInfo = item.reportCount > 0 ? '<span style="margin: 0 10px;">|</span><a data-toggle="modal" data-target="#reportModal" data-comment_num="'+item.comment_num+'">신고내용</a>' : '';
		
		content += '<div class="comment d-flex mb-4"><a href="/board/detail/'+item.post_num+'"><div class="flex-shrink-0"><div class="avatar avatar-sm rounded-circle">'+
		'<img class="avatar-img" src="'+profileImg+'" alt=""></div></div><div class="flex-grow-1 ms-2 ms-sm-3"><div class="comment-meta d-flex align-items-baseline">'+
		'<h6 class="me-2">'+item.nickname+'</h6><span class="text-muted">'+item.comment_date+'</span></div><div class="comment-body">'+item.comment+'</div></a>'+
		'<div><div class="card-body text-end">추천수 : '+item.recommend+'<span style="margin: 0 10px;">|</span>신고수 : '+item.reportCount+''+reportInfo+''+
		''+commentClear+'</div>'+
		'</div></div></div></div></div>';
	})
	
		$("#boardList").empty();
	$("#boardList").append(content);
}

$(function(){
	$("select").on("input change", function(){
		var category = document.getElementById("state");
		state = category.options[category.selectedIndex].value;
		$('#pagination').twbsPagination('destroy');
		if(group == "" || group == "board"){
			listAdd(page, search_word, sort, direction, state);	
		}else{
			commentList(1, commentSearch_word, sort, direction, state);
		}
	})
})

function commentClear(comment_num){
	var check = confirm("이 댓글의 블라인드를 해제하시겠습니까?");
	
	if(check){
		$.ajax({
			type : 'post',
			url : '/manage/commentClear',
			data : {'comment_num':comment_num},
			dataType : 'json',
			success : function(data){
				if(data == 1){
					commentList(page, commentSearch_word, sort, direction, state);	
				}
			},
			error : function(e){
				console.log(e);
			}
		})
	}
}
