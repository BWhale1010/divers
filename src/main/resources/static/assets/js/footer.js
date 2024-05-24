footerList();

function footerList(){
	$.ajax({
		type : 'post',
		url : '/main/recommendList',
		dataType : 'json',
		success : function(data){
			foothumbnail(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function foothumbnail(list) {
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
						footerdraw(list);
					}
                },
                error: function(e) {
                    console.log(e);
                    complet++;
                    
                    if(complet === list.length){
						footerdraw(list);
					}
                }
            });
    }
}

function footerdraw(list){
	var content = '';
	
	list.forEach(function(item){
		const thumbnailSrc = item.thumbnail === 'basic' ? '/assets/img/divers_thumbnail.png' : 'data:image/jpeg;base64,' + item.thumbnail;
		
		content += '<li><a href="/board/detail/'+item.post_num+'" class="d-flex align-items-center">'+
                  '<img src="'+thumbnailSrc+'" alt="" class="img-fluid me-3"><div>'+
                    '<div class="post-meta d-block"><span class="date">'+item.small_class_name+'</span>'+
                    '<span class="mx-1">&bullet;</span> <span>'+item.board_date+'</span></div>'+
                    '<span>'+item.content+'</span></div></a></li>'
	})
	
	$("#footerList").empty().append(content);
	
}