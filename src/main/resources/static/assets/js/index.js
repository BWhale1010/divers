recommendList();
newList();

function recommendList(){
	$.ajax({
		type : 'post',
		url : '/main/recommendList',
		dataType : 'json',
		success : function(data){
			thumbnail(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

// Swiper 인스턴스 변수 선언
let swiperInstance;

function drawRecommend(list){
    let content = '';
    console.log(list);
    list.forEach(function(item){
        const thumbnailSrc = item.thumbnail === 'basic' ? '/assets/img/divers_thumbnail.png' : 'data:image/jpeg;base64,' + item.thumbnail;
        
        content += '<div class="swiper-slide"><div class="recommend-container">'+
        '<a href="/board/detail/'+item.post_num+'" class="img-bg d-flex align-items-end" style="background-image: url(\''+thumbnailSrc+'\');">'+
        '<div class="img-bg-inner">'+
        '<h2>'+item.title+'</h2>'+
        '<p>'+item.content+'</p>'+
        '</div></a></div></div>';
    });
    
    $("#recommendList").empty().append(content);

    // Swiper 인스턴스 초기화 또는 재초기화
    if (swiperInstance) {
        swiperInstance.destroy(true, true);
    }
    
    swiperInstance = new Swiper('.sliderFeaturedPosts', {
        loop: true,
        navigation: {
            nextEl: '.custom-swiper-button-next',
            prevEl: '.custom-swiper-button-prev',
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        autoplay: {
            delay: 3000,
            disableOnInteraction: false,
        },
    });
}

function recommendInfo(info){
	const exampleList = [
    { post_num: info[0].post_num, thumbnail:  info[0].thumbnail, title: info[0].title, content: info[0].content },
    { post_num: info[1].post_num, thumbnail:  info[1].thumbnail, title: info[1].title, content: info[1].content },
    { post_num: info[2].post_num, thumbnail:  info[2].thumbnail, title: info[2].title, content: info[2].content },
    { post_num: info[3].post_num, thumbnail:  info[3].thumbnail, title: info[3].title, content: info[3].content }
	];
	drawRecommend(exampleList);
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
						recommendInfo(list);
					}
                },
                error: function(e) {
                    console.log(e);
                    complet++;
                    
                    if(complet === list.length){
						recommendInfo(list);
					}
                }
            });
    }
}

function newList(){
	$.ajax({
		type : 'post',
		url : '/main/newList',
		dataType : 'json',
		success : function(data){
			newDraw(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function newDraw(list){
	var content = '';
	
	for(let i = 0; i<list.length; i++){
		content += '<li><a href="/board/detail/'+list[i].post_num+'"><span class="number">'+(i+1)+'</span>'+
		'<h3>'+list[i].title+'</h3><span class="author">'+list[i].nickname+'</span></a></li>'
	}
	
	$("#newList").empty().append(content);
}