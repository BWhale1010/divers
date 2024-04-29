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
