recommendList();
newList();
popList();

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

function popList(){
	$.ajax({
		type : 'post',
		url : '/main/popList',
		dataType : 'json',
		success : function(data){
			popThumbnail(data.list);
		},
		error : function(e){
			console.log(e);
		}
	})
}

function popDraw(list){
	var content1 = '';
	var content2 = '';
	var content3 = '';
	var thumbnail = [];
	var profileImg  = [];
	for(let i = 0; i<list.length; i++){
		thumbnail[i] = list[i].thumbnail === 'basic' ? '/assets/img/divers_thumbnail.png' : 'data:image/jpeg;base64,' + list[i].thumbnail;
		profileImg[i] = list[i].new_filename === null ? '/assets/img/profile.png' : "/photo/" + list[i].new_filename;
	}
	
	content1 += '<div class="post-entry-1 lg">'+
              '<a href="/board/detail/'+list[0].post_num+'"><img src="'+thumbnail[0]+'" alt="" class="img-fluid"></a>'+
              '<div class="post-meta"><span class="date">'+list[0].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[0].board_date+'</span></div>'+
              '<h2><a href="single-post.html">'+list[0].title+'</a></h2>'+
              '<p class="mb-4 d-block">'+list[0].content+'</p>'+
              '<div class="d-flex align-items-center author">'+
                '<div class="photo"><img src="'+profileImg[0]+'" alt="" class="img-fluid"></div>'+
                '<div class="name"><h3 class="m-0 p-0">'+list[0].nickname+'</h3></div></div>';
                
    content2 +=  '<div class="post-entry-1">'+
             '<a href="/board/detail/'+list[1].post_num+'"><img src="'+thumbnail[1]+'" alt="" class="img-fluid"></a>'+
             '<div class="post-meta"><span class="date">'+list[1].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[1].board_date+'</span></div>'+
                  '<h2><a href="/board/detail/'+list[1].post_num+'">'+list[1].title+'</a></h2></div>'+
                '<div class="post-entry-1"><a href="/board/detail/'+list[2].post_num+'"><img src="'+thumbnail[2]+'" alt="" class="img-fluid"></a>'+
                  '<div class="post-meta"><span class="date">'+list[2].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[2].board_date+'</span></div>'+
                  '<h2><a href="/board/detail/'+list[2].post_num+'">'+list[2].title+'</a></h2></div>'+
                '<div class="post-entry-1">'+
                  '<a href="/board/detail/'+list[3].post_num+'"><img src="'+thumbnail[3]+'" alt="" class="img-fluid"></a>'+
                  '<div class="post-meta"><span class="date">'+list[3].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[3].board_date+'</span></div>'+
                  '<h2><a href="/board/detail/'+list[3].post_num+'">'+list[3].title+'</a></h2>'+
                '</div>';
                
       content3 += '<div class="post-entry-1">'+
                  '<a href="/board/detail/'+list[4].post_num+'"><img src="'+thumbnail[4]+'" alt="" class="img-fluid"></a>'+
                  '<div class="post-meta"><span class="date">'+list[4].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[4].board_date+'</span></div>'+
                  '<h2><a href="/board/detail/'+list[4].post_num+'">'+list[4].title+'</a></h2></div>'+
                '<div class="post-entry-1"><a href="/board/detail/'+list[5].post_num+'"><img src="'+thumbnail[5]+'" alt="" class="img-fluid"></a>'+
                  '<div class="post-meta"><span class="date">'+list[5].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[5].board_date+'</span></div>'+
                  '<h2><a href="/board/detail/'+list[5].post_num+'">'+list[5].small_class_name+'</a></h2></div>'+
                '<div class="post-entry-1"><a href="/board/detail/'+list[6].post_num+'"><img src="'+thumbnail[6]+'" alt="" class="img-fluid"></a>'+
                  '<div class="post-meta"><span class="date">'+list[6].small_class_name+'</span> <span class="mx-1">&bullet;</span> <span>'+list[6].board_date+'</span></div>'+
                  '<h2><a href="/board/detail/'+list[6].post_num+'">'+list[6].title+'</a></h2></div>';
                  
                  $("#popList1").empty().append(content1);
                  $("#popList2").empty().append(content2);
                  $("#popList3").empty().append(content3);
	
}

function popThumbnail(list) {
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
						popDraw(list);
					}
                },
                error: function(e) {
                    console.log(e);
                    complet++;
                    
                    if(complet === list.length){
						popDraw(list);
					}
                }
            });
    }
}