$(function(){
	$(".hover-join, .hover-login, .hover-mypage, .hover-logout").hover(function(){
		var text = $(this).data("text");
		$(this).append("<span class='hover'>"+text+"</span>");
	}, function(){
		$(this).find(".hover").remove();
	});
});
