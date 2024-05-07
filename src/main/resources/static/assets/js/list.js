var total = 5;
var boardId  = $("#boardId").val();

var flag = true;

function flag(){
	if(!flag){
		flag = true;
	}
}


listAdd(1, boardId);

function listAdd(page, boardId){
	console.log("category : "+boardId);
	$.ajax({
		type: 'post',
		url : '/board/listAdd',
		data : {'page' : page, 'category' : boardId},
		dataType : 'json',
		success: function(data){
			thumbnail(data.list);
			
			console.log(data.list);
//			var total = data.total;
			
			$('#pagination').twbsPagination({
				startPage : 1,
				totalPages: data.total,
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
	//console.log("aa : "+ list[0].thumbnail);
	var user_num = $("#user_num").val();
	console.log("user_num : "+user_num);
	if(user_num == ""){
		user_num = 0;
		console.log("user_num : "+user_num);
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
        '<span class="date">조회수 ' + item.count + '</span> <span class="mx-1">•</span> <span>추천수 ' + item.recommend + '</span></div></div></div>';
	});

	
	$("#boardList").empty();
	$("#boardList").append(content);
	
}

function thumbnail(list) {
	var complet = 0;
	
    for (let i = 0; i < list.length; i++) {
        console.log("썸네일 요청 : " + list[i].post_num);

            $.ajax({
                type: 'post',
                url: '/board/thumbnail/' + list[i].post_num,
                dataType: 'json',
                success: function(data) {
                    list[i].thumbnail = data.thumbnail;
                    list[i].content = data.text;
                    console.log("여기 : "+list[i].thumbnail);
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




