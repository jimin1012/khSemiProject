const navCol7 = document.getElementsByClassName("navCol7")[0];
const pathname = location.pathname;
if(location.pathname.substring(13, pathname.indexOf("/post", 1)) == "report"){
    navCol7.setAttribute("style" , "font-size: 25px");
}

// 신고부분 뒤로가기
const callbackPage = document.getElementById("callbackPage");
if(callbackPage != null){
    callbackPage.addEventListener("click" , function(){
        history.go(-1);
    })
}

// 신고된 게시글 더보기 기능
var detailAreas = document.querySelectorAll('.detailArea');
var currentIndex = 0;

// 처음에 페이지 로드될 때 처음 3개의 요소를 보이도록 설정
for (var i = 0; i < 3; i++) {
    if (currentIndex < detailAreas.length) {
    detailAreas[currentIndex].style.display = 'flex';
    currentIndex++;
    } else {
      break;  // 요소가 부족할 경우 루프 종료
    }
}

const showMoreBtn = document.getElementById('showMore');
function displayNone(){
    showMoreBtn.style.display = 'none';
}
function showMore() {
    for (var i = 0; i < 3; i++) {
        if (currentIndex < detailAreas.length) {
        detailAreas[currentIndex].style.display = 'flex';
        currentIndex++;
        } else {
        showMoreBtn.style.display = 'none';
        alert('더이상 신고된 게시글이 없습니다.\n(나머지[마지막] 내역을 불러옵니다.)');
        displayNone();
        break;
        }
    }
}