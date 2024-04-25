var total = 5;
var boardId  = $("#boardId").val();

listAdd(1, boardId);

function listAdd(page, boardId){
	console.log("category : "+boardId);
	$.ajax({
		type: 'post',
		url : '/board/listAdd',
		data : {'page' : page, 'category' : boardId},
		daatType : 'json',
		success: function(data){
			listDraw(data.list);
			console.log(data.list);
			var total = data.total;
			
			$('#pagination').twbsPagination({
				startPage : 1,
				totalPages: total,
				visiblePages: 5,
				onPageClick: function(e,page){
					console.log(e);
					listAdd(page, boardId);
				}
			});
		},
		error:function(e){
			console.log(e);
		}
	})		
}

function listDraw(list){
	var content ="";
	console.log("추천수 : "+list[0].recommend);
	
	list.forEach(function(item){
	    content += 
	   '<div class="d-md-flex post-entry-2 half"><a href="#" class="me-4 thumbnail"><img src="/assets/img/post-landscape-6.jpg" alt="" class="img-fluid"></a><div><div class="post-meta"><span class="date">'+item.small_class_name+'</span> <span class="mx-1">•</span><span>'+item.board_date+'</span></div><h3><a href="#">'+item.title+'</a></h3><p>'+item.content+'</p><div class="d-flex align-items-center author"><div class="photo"><img src="#" alt="" class="img-fluid"></div><div class="name"><h3 class="m-0 p-0">'+item.nickname+'</h3></div></div><div class="post-meta"><span class="date">조회수 '+item.count+'</span> <span class="mx-1">•</span> <span>추천수 '+item.recommend+'</span></div></div></div>';
	});

	
	$("#boardList").empty();
	$("#boardList").append(content);
	
}

