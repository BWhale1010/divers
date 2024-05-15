$('#reportModal').on('show.bs.modal', function (event) {
    var post_num = $(event.relatedTarget).data('post_num');
    modalInfo(post_num);
});

function modalInfo(post_num){
	
	$.ajax({
		type : 'post',
		url : '/manage/reportInfo',
		data : {'post_num':post_num},
		dataType : 'json', 
		success : function(data){
			reportDraw(data);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function reportDraw(report){
	var content = '';
	content += '<div class="post-meta"><span class="date">'+report.small_class_name+' </span><span class="mx-1">•</span><span>'+report.board_date+'</span>'+
              				' | <span class="date">'+report.nickname+'</span><span class="mx-1">•</span> <span>조회수 '+report.count+'</span><span class="mx-1">•</span><span>추천수 '+report.recommend+'</span>'+
              				' | <span class="date">신고수 '+report.reportCount+'</span><span class="mx-1">•</span><span>'+report.post_state_name+'</span></div>'+
              				'<h1 class="mb-3">신고 내용</h1><p>'+report.report_detail+'</p>';
              				
    $("#reportInfo").empty().append(content);
}

$(function(){

})