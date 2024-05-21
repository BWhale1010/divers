let index ={
	
	init: function(){
		
		$(function(){
			$("input[type='text'], .note-editor, select").on("input change", function(){
				var cid = this.id;
				if(cid == "title" ){
					$(".msg1").html("");
				}else if(cid == "category"){
					$(".msg2").html("");
				}else{
					$(".msg3").html("");
				}
					
			})
		});
		
		$("#write-btn").on("click", ()=>{
			this.writeCheck();
		});
		$(document).ready("input", ()=>{
			this.inputMsg();
		});
	
	}
	
	,writeCheck:function(){
			var title =$("#title").val();
			var category = document.getElementById("category");
			var option = category.options[category.selectedIndex].value;
			var content = $("#content").val();
			
			
			if(option == "init"){
				option = null;
			}
			
			var nullCheck = [];
			
			if(!title){
				nullCheck.push("title");
			}else if(!option){
				nullCheck.push("option");
			}else if(!content){
				nullCheck.push("content");
			}else{
				index.writeSave(title, option, content);
			}
			
			if(nullCheck.length > 0){
				index.nullCheckFunc(nullCheck);
			}
	}
	,writeSave:function(title, category, content){
		confirm("작성 글을 저장하시겠습니까?");
		
		if(confirm){
			let param ={
				title : title,
				category : category,
				content : content
			}
				$.ajax({
				type:"post",
				url:"/board/write",
				data: param,
				dataType: "json",
				success: function(data){
					location.href="/board/detail/"+data;
				},
				error: function(e){
					console.log(e);
				}
			})
		}
	}
	,nullCheckFunc:function(nullCheck){
		nullCheck.forEach(function(elem){
			switch(elem){
				case "title":
					$(".msg1").html("제목을 입력해주세요.");
				break;
				case "option":
						$(".msg2").html("카테고리를 선택해주세요.");
				break;
				case "content":
					$(".msg3").html("내용을 입력해주세요.");
				break;
			}
		})
	}
	
}

index.init();