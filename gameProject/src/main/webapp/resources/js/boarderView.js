// 목록으로 버튼
const callback = document.getElementById("callback");
(function(){

    if(callback != null){ 
        
        callback.addEventListener("click", function(){

            const pathname = location.pathname;

            let url = pathname.substring(0, pathname.indexOf("/", 1));

            url += "/BoardList?";

            const params = new URL(location.href).searchParams;

            const kind = "kind=" + params.get("kind");

            let cp;

            if(params.get("cp") != null){ // 쿼리스트링에 cp가 있을 경우
                cp = "cp=" + params.get("cp"); // cp = 1
            }else{ // 쿼리스트링에 cp가 없을 경우
                cp = "cp=1"
            }

            // 조립
            url += kind + "&" + cp

            // 검색 key, query가 존재하는 경우 url에 추가
            if(params.get("key") != null){
                const key = "&key=" + params.get("key");
                const query = "&query=" + params.get("query");

                url += key + query;
            }


            // location.href = "주소"; -> 해당 주소로 이동
            location.href = url;
        })

    }

})();


// 뒤로가기(목록으로)
function toGoback(){
    history.go(-1);
}


// 게시글신고 글씨 눌렀을 때 효과(신고하는작성창 숨기고 보이기)
const borderReport = document.getElementById("borderReport");
const reportBtn = document.getElementById("reportBtn");
const boarderReportBtn = document.getElementById("boarderReportBtn");

borderReport.style.display = 'none'; // 먼저숨김
reportBtn.style.display = 'none'; // 먼저숨김

if(boarderReportBtn != null){

    boarderReportBtn.addEventListener("click" , function(){
        
        if(borderReport.style.display !== 'none'){
            borderReport.style.display = 'none';
            reportBtn.style.display = 'none';
        }else{
            borderReport.style.display = '';
            reportBtn.style.display = '';
        }
    })

}


// 게시글신고 글씨 눌렀을때 나오는 닫기 버튼 눌렀을 때도 닫히는 기능
const reportBtnClose = document.getElementById("reportBtnClose");
reportBtnClose.addEventListener("click" , function(){
    borderReport.style.display = 'none';
    reportBtn.style.display = 'none';
})



// 게시글 신고할 때 작성하고 신고 버튼 눌렀을 때 확인하는 알림창
const report = document.getElementById("report");
const reportText = document.getElementById("reportText");
report.addEventListener("click" , function(){

    if(reportText.value == ''){
        alert("신고내용을 작성해주세요.");
        reportText.focus();
    }else{

        alert("신고가 정상 처리되었습니다.");
        borderReport.style.display = 'none';
        reportBtn.style.display = 'none';
    
        $.ajax({
            url : contextPath+"/view/report",
            data : {"boardNo" : boardNo , "reportContent" : reportText.value},
            type : "GET",
            success : function(result){
            },
            error : function(req, status,error){
                console.log("신고 실패");
                console.log(req.responseText);
            }
        })

        textDel();
        toGoback();
    }


})

// 신고 내용 삭제 함수
function textDel(){
    reportText.innerText = "";
    reportText.value = "";
}


const cancelBoard = document.getElementById("cancelBoard");
// 신고 목록에서 제거 하기 - 관리자용
if(cancelBoard != null){
	cancelBoard.addEventListener("click" , function(){
    
    
    $.ajax({
        url : contextPath+"/view/cancel",
        data : {"boardNo" : boardNo},
        type : "GET",
        success : function(result){

            if(result > 0){
                alert("신고목록에서 제거 완료");

            }else{
                alert("신고목록에서 제거 실패");
            }
        },
        error : function(req, status,error){
            console.log("반려처리도중 오류");
            console.log(req.responseText);
        }
    });
    
})

}

// 게시글 목록에서 제거 하기(삭제) - 관리자용
function handleButtonClick(boardNo) {

    $.ajax({
        url: contextPath + "/view/cancel",
        data: { "boardNo": boardNo },
        type: "GET",
        success: function (result) {

            if (result > 0) {
                alert("신고목록에서 제거 완료");
                $.ajax({
                    url: contextPath + "/view/delete",
                    data: { "boardNo": boardNo },
                    type: "GET",
                    success: function (result) {

                        if (result > 0) {
                            alert("삭제 처리 완료");
                        } else {
                            alert("삭제 처리 실패");
                        }
                    },
                    error: function (req, status, error) {
                        console.log("삭제처리도중 오류");
                        console.log(req.responseText);
                    }
                });

                toGoback();

            } else {
                alert("신고목록에서 제거 실패");
            }
        },
        error: function (req, status, error) {
            console.log("반려처리도중 오류");
            console.log(req.responseText);
        }
    });

}

$("#deleteBoard").on("click", function () {
    handleButtonClick(boardNo);
});