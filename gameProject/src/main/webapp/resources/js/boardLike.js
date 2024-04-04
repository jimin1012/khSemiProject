// 기초 화면 표기
(function(){
    boardLikeCountView();
})();

// 좋아요 버튼 클릭 시 이벤트 활성화
document.getElementById("like").addEventListener("click", function () {
    // 좋아요 판단
    boardLikeCheck();
});

// 좋아요 판단 함수
function boardLikeCheck() {
    $.ajax({
        url: contextPath + "/view/boardLikeCheck",
        data: {
            "boardNo": boardNo,
            "memberNo": loginMemberNo
        },
        type: "GET",
        dataType: "JSON",
        success: function (check) {

            // 토글 처리
            if (check === 1) {
                // 이미 좋아요한 경우 - 좋아요 취소
                deleteTest();
            } else {
                // 좋아요하지 않은 경우 - 좋아요 추가
                plusTest();
            }
        },
        error: function () {
            console.log("좋아요 판단하는데 오류 남");
        }
    });
}

// 좋아요 추가 함수
function plusTest() {
    $.ajax({
        url: contextPath + "/view/boardLikeUpdate",
        data: {
            "boardNo": boardNo,
            "memberNo": loginMemberNo
        },
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            boardLikeCountView();
        },
        error: function () {
            console.log("좋아요 추가 하는데 오류남");
        }
    });
}

// 좋아요 삭제 함수
function deleteTest() {
    $.ajax({
        url: contextPath + "/view/boardLikeDelete",
        data: {
            "boardNo": boardNo,
            "memberNo": loginMemberNo
        },
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            boardLikeCountView();
        },
        error: function () {
            console.log("좋아요 삭제 하는데 오류남");
        }
    });
}

// 게시글 좋아요 숫자 표시 함수
function boardLikeCountView() {
    $.ajax({
        url: contextPath + "/view/boardLikeCount",
        data: {
            "boardNo": boardNo
        },
        type: "GET",
        dataType: "JSON",
        success: function (boardLikeCount) {
            // const boardLikeCountJone = document.getElementById("boardLikeCount");
            const likeBtn = document.getElementById("like");

            if (likeBtn) {
                likeBtn.innerText = " " + boardLikeCount;
            }
        },
        error: function () {
            console.log("좋아요 숫자 띄우는데 오류남");
        }
    });
}
