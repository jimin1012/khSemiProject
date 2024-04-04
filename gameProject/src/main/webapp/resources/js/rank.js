// 랭킹 조회용 count
let count;
let list;

// 랭킹 조회용 ajax
const cate = document.getElementById("cate");
const ranking = document.getElementsByClassName("ranking")[0];

cate.addEventListener("change", function(){
    ajxaCall(cate.value);
});

window.onload = function(){
    ajxaCall(1);
}

const modal = document.querySelector(".modal");
const modalClose = document.getElementById("modal-close");
const inputName = document.getElementById("nameSearch");

function searchRank(){
    if (window.event.keyCode == 13) {
        let countRank = 1;
        modal.innerHTML = ""; // modal 안의 내용 비움
    
        // modal 안쪽에 들어갈 영역 생성
        const modalSpan = document.createElement("span");
        const div1 = document.createElement("div");
        const div2 = document.createElement("div");
        const title = document.createElement("span");
    
        // title div2 안에 들어가야함
        title.classList.add("modalTitle");
        title.innerText = "검색된 랭킹";
    
        // X 생성
        modalSpan.setAttribute("id", "modal-close");
        modalSpan.innerHTML = "&times;";
        modalSpan.addEventListener("click", closeModal);
        
        // modal 보여주는 클래스 추가
        modal.classList.toggle("show");
    
        for(r of list){
            if(r.memberName == inputName.value){
                
                // 큰 영역의 div
                const modalContent = document.createElement("div");
                modalContent.classList.add("modal-content");
    
                // 첫 번째 div
                const contentDiv1 = document.createElement("div");
                const img = document.createElement("img");
                if(r.profile == null){
                    img.setAttribute("src", contextPath+"/resources/images/프로필 기본값.png");    
                }else{
                    img.setAttribute("src", contextPath + r.profile);
                }
                img.classList.add("trophy");
                contentDiv1.append(img);
                
                // 두 번째 div
                const contentDiv2 = document.createElement("div");
                const nameDiv = document.createElement("div");
                nameDiv.innerText = r.memberName;
                const scoreDiv = document.createElement("div");
                scoreDiv.innerText = r.score + "점";
                contentDiv2.append(nameDiv, scoreDiv);
                contentDiv2.classList.add("ranking-center");
    
                // 세 번째 div
                const contentDiv3 = document.createElement("div");
                const rankingDiv = document.createElement("div");
                rankingDiv.innerText = countRank + "위";
                contentDiv3.append(rankingDiv);
    
                modalContent.append(contentDiv1, contentDiv2, contentDiv3);
                div2.append(modalContent);
            }
            countRank++;
        }
    
        if(div2.innerHTML == ""){ // 검색된 이름이 없을 때
            title.innerText = "검색된 이름이 없습니다.";
        }
    

        div2.prepend(modalSpan, title);
        div1.append(div2);
    
    
        modal.append(div1); // X 버튼 추가

    }

}

// modal 부분 (이름 검색 시 나오는 부분)
function closeModal(){
    
    modal.classList.toggle("hide"); // hide 클래스 추가

    setTimeout(function(){ // 0.45초 후 동작

        modal.classList.toggle("hide"); // hide 클래스 제거

        modal.classList.toggle("show"); // remove

    }, 450);
    
}

















//-----------------------------------------------------------------------------------
// 처음 페이지 로딩 시 작동하는 코드
function selectRank(result){

    const rankTitle = document.getElementById("rankTitle");
        list = result;
    
        rankTitle.innerText = "";
    
        const rankDiv = document.getElementsByClassName("rank-div");
        ranking.innerHTML = "";
        let c = 0;
        rankDiv[0].innerHTML = ""; // 1~3위 랭킹 부분 비우기
        rankDiv[1].innerHTML = "";
        rankDiv[2].innerHTML = "";


    if(result.length == 0){
        modal.innerHTML = ""; // modal 안의 내용 비움

        const modalSpan = document.createElement("span");
        const div1 = document.createElement("div");
        const div2 = document.createElement("div");
        const title = document.createElement("span");
    
        // title div2 안에 들어가야함
        title.classList.add("modalTitle");
        title.innerText = "조회된 랭킹이 없습니다.";

        // X 생성
        modalSpan.setAttribute("id", "modal-close");
        modalSpan.innerHTML = "&times;";
        modalSpan.addEventListener("click", closeModal);
        
        // modal 보여주는 클래스 추가
        modal.classList.toggle("show");

        div2.prepend(modalSpan, title);
        div1.append(div2);
        modal.append(div1); // X 버튼 추가
    }else{
        const rankTitle = document.getElementById("rankTitle");
        list = result;
    
        rankTitle.innerText = result[0].gameName;
    
        const rankDiv = document.getElementsByClassName("rank-div");
        ranking.innerHTML = "";
        let c = 0;
        rankDiv[0].innerHTML = ""; // 1~3위 랭킹 부분 비우기
        rankDiv[1].innerHTML = "";
        rankDiv[2].innerHTML = "";
        for(r of result){
            // 1~3위
            if(c <= 2){
                let profileDiv = document.createElement("div"); // 프로필 사진 들어갈 div
                let profileImg = document.createElement("img"); // 프로필 사진 들어갈 img
                profileImg.classList.add("rank-top-img");
                if(r.profile == null){
                    profileImg.setAttribute("src", contextPath+"/resources/images/프로필 기본값.png");    
                }else{
                    profileImg.setAttribute("src", contextPath+r.profile);
                }
    
                profileDiv.append(profileImg); // div에 추가
                
                let topRankDiv = document.createElement("div"); // 순위, 이름, 기록 들어갈 div
                topRankDiv.classList.add("top-ranking");
    
                let span1 = document.createElement("span"); // 순위
                let span2 = document.createElement("span"); // 이름
                let span3 = document.createElement("span"); // 기록
                span2.innerText = r.memberName;
                span3.innerText = r.score + "점";
                span1.innerText = (c+1)+"위";
                
    
                topRankDiv.append(span1, document.createElement("br"), span2, document.createElement("br"), span3); // div에 추가
    
                switch(c){
                    case 0 : rankDiv[1].append(profileDiv, topRankDiv);break;
                    case 1 : rankDiv[0].append(profileDiv, topRankDiv);break;
                    case 2 : rankDiv[2].append(profileDiv, topRankDiv);break;
                }
            }
            
            // 3위 이후 
            if(c > 2){
                const mainDiv = document.createElement("div");
                const imgDiv = document.createElement("div");
                const infoDiv = document.createElement("div");
                infoDiv.classList.add("ranking-center");
                const rankDiv = document.createElement("div");
                rankDiv.innerText = (c+1) + "위";
                
                const img = document.createElement("img");
                if(r.profile == null){
                    img.setAttribute("src", contextPath + "/resources/images/프로필 기본값.png");
                }else{
                    img.setAttribute("src", contextPath + r.profile);
                }
                img.classList.add("trophy");
                const name = document.createElement("div");
                name.innerText = r.memberName;
                // 점수
                const so = document.createElement("div");
                so.innerText = r.score + "점";
    
                infoDiv.append(name, so);
                imgDiv.append(img);
                mainDiv.append(imgDiv, infoDiv, rankDiv);
    
                
                ranking.append(mainDiv);
                
            }
            if(++c > 9){
                count = c;
                const nextBtn = document.createElement("button");
                nextBtn.classList.add("nextBtn");
                nextBtn.innerText = "더보기";
                nextBtn.addEventListener("click", addRanking);
                ranking.append(nextBtn);
                // list.splice(0, 10); // 랭킹 10개 삭제
                break;
            }
        } // for문 끝
    }
}


// ajax
function ajxaCall(categoryNo){
    $.ajax({
        url : "rank",
        type : "GET",
        dataType : "JSON",
        data : {"category" : categoryNo},
        success : function(result){
            selectRank(result);
        },
        error : function(request, status, error){
            console.log("AJAX 에러 발생");
            console.log("상태코드 : " + request.status); // 404, 500
            console.log(request.responseText); // 에러 메시지
            console.log(error); //에러 객체 출력
        }
    });
}

// 더보기 버튼 클릭시 랭킹 10개씩 출력
function addRanking(){
    
    let c = count;
    
    document.getElementsByClassName("nextBtn")[0].remove();

    for(c; c <= count+9; c++){
        const mainDiv = document.createElement("div");
        const imgDiv = document.createElement("div");
        const infoDiv = document.createElement("div");
        infoDiv.classList.add("ranking-center");
        const rankDiv = document.createElement("div");
        rankDiv.innerText = (c+1) + "위";
        
        const img = document.createElement("img");
        if(list[c].profile == null){
            img.setAttribute("src", contextPath + "/resources/images/프로필 기본값.png");
        }else{
            img.setAttribute("src", contextPath + list[c].profile);
        }
        img.classList.add("trophy");
        const name = document.createElement("div");
        name.innerText = list[c].memberName;
        // 점수
        const so = document.createElement("div");
        so.innerText = list[c].score;

        infoDiv.append(name, so);
        imgDiv.append(img);
        mainDiv.append(imgDiv, infoDiv, rankDiv);

        ranking.append(mainDiv);
    }
    // list.splice(0, 10); // 불러온 랭킹 리스트 10개 삭제
    count += 10;
    if(list.length != count){
        const nextBtn = document.createElement("button");
        nextBtn.classList.add("nextBtn");
        nextBtn.innerText = "더보기";
        nextBtn.addEventListener("click", addRanking);
        ranking.append(nextBtn);
    }
}