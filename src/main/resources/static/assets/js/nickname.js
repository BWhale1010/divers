const places = ["에버랜드", "맥도날드", "서울타워", "제주도", "한강공원", "롯데월드", "경복궁", "남산", "강릉해변", "부천아울렛", "명동거리", "동대문", "한라산", "속초해변", "인천공항", "강남역", "홍대", "부산해운대", "전주한옥마을", "제주올레길", "북촌한옥마을", "광안리해수욕장", "대구타워", "경주불국사", "설악산", "남해안", "해남녹차밭", "진주성", "통영케이블카", "거제도", "수원화성", "대전엑스포", "울산바위", "포항해변", "목포해양대", "군산항", "정동진", "강화도", "춘천호수", "남이섬", "오대산", "무주리조트", "양양서핑", "삼척동굴", "여의도공원", "김포공항"];
const adjectives = ["기쁜", "슬픈", "빠른", "느린", "밝은", "어두운", "강한", "약한", "재미있는", "지루한", "용감한", "겁많은", "똑똑한", "어리석은", "예쁜", "귀여운", "멋진", "평온한", "신나는", "차가운", "따뜻한", "반짝이는", "신비로운", "활기찬", "상냥한", "매력적인", "독특한", "흥미로운", "활발한", "부드러운", "상쾌한", "진지한", "시원한", "따스한", "포근한", "청량한", "담백한", "화려한", "수줍은", "정겨운", "섹시한", "젠틀한", "로맨틱한", "황홀한", "신속한", "믿음직한"];
const animals = ["곰", "사자", "호랑이", "늑대", "여우", "토끼", "다람쥐", "고양이", "강아지", "말", "독수리", "팬더", "코알라", "캥거루", "원숭이", "사슴", "수달", "고래", "상어", "펭귄", "하마", "기린", "코끼리", "낙타", "양", "염소", "치타", "나무늘보", "올빼미", "오리너구리", "악어", "비둘기", "청설모", "너구리", "해마", "바다사자", "문어", "갈매기", "잉어", "돌고래", "참새", "앵무새", "하늘소", "장수풍뎅이", "사마귀", "개구리"];

function getRandomIndex(array) {
    return Math.floor(Math.random() * array.length);
}

function generateRandomNickname() {
    const randomPlace = places[getRandomIndex(places)];
    const randomAdjective = adjectives[getRandomIndex(adjectives)];
    const randomAnimal = animals[getRandomIndex(animals)];
    return randomPlace + "의" + randomAdjective + "" + randomAnimal;
}

$("#nickname-btn").on("click", function(){
	const nickname = generateRandomNickname();
	$("#nickname").val(nickname);
	$("#nickname").focus();
})
