recommendSideList();
popSideList();
newSideList();

function recommendSideList(){
	$.ajax({
		type : 'post',
		url : '/board/recommendList',
		dataType : 'json',
		success : function(data){
			drawRecommend(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function drawRecommend(list){
    let content = '';
    list.forEach(function(item){
        
        content += '<div class="post-entry-1 border-bottom">'+
                    '<div class="post-meta"><span class="date">'+item.small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+item.board_date+'</span></div>'+
                    '<h2 class="mb-2"><a href="/board/detail/'+item.post_num+'" class="titleLimit2">'+item.title+'</a></h2>'+
                    '<span class="author mb-3 d-block">'+item.nickname+'</span></div>';
    });
    
    $("#pills-popular").empty().append(content);

}

function popSideList(){
	$.ajax({
		type : 'post',
		url : '/board/popList',
		dataType : 'json',
		success : function(data){
			drawPop(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function drawPop(list){
    let content = '';
    list.forEach(function(item){
        
        content += '<div class="post-entry-1 border-bottom">'+
                    '<div class="post-meta"><span class="date">'+item.small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+item.board_date+'</span></div>'+
                    '<h2 class="mb-2"><a href="/board/detail/'+item.post_num+'" class="titleLimit2">'+item.title+'</a></h2>'+
                    '<span class="author mb-3 d-block">'+item.nickname+'</span></div>';
    });
    
    $("#pills-trending").empty().append(content);

}

function newSideList(){
	$.ajax({
		type : 'post',
		url : '/board/newList',
		dataType : 'json',
		success : function(data){
			drawNew(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function drawNew(list){
    let content = '';
    list.forEach(function(item){
        
        content += '<div class="post-entry-1 border-bottom">'+
                    '<div class="post-meta"><span class="date">'+item.small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+item.board_date+'</span></div>'+
                    '<h2 class="mb-2"><a href="/board/detail/'+item.post_num+'" class="titleLimit2">'+item.title+'</a></h2>'+
                    '<span class="author mb-3 d-block">'+item.nickname+'</span></div>';
    });
    
    $("#pills-latest").empty().append(content);

}