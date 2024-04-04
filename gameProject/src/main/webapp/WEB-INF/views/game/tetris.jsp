<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>테트리스</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/game/tetris.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
    <script src="https://kit.fontawesome.com/0ddb604158.js" crossorigin="anonymous"></script>

</head>
<body>
    <div class="wrap">
        <!-- header include -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <div class="main-footer-wrap">
            <main>
                <div class="popup">
                    <div>
                        <p id="ptxt1">게임종료!</p>
                        <p id="ptxt2">게임 스코어를 저장하시겠습니까?</p>
                    </div>
                    <div>
                        <button id="saveScore-btn">예</button>
                        <button class="restart">아니오</button>
                    </div>
                </div>
                <div class="level-score-wrap">
                    <div class="level_container">
                        <p>속도</p>
                        <div class="level"></div>
                    </div>
                    <div class="score_container">
                        <p>점수</p>
                        <p class="score">0</p>
                    </div>
                </div>
                <div class="board">
                    <div class="header">
                        <p>테트리스 게임</p>
                    </div>
                    <table class="stage_container">
                        <tbody class="stage"></tbody>
                    </table>
                </div>
                <div class="next"></div>
            </main>
            <!-- 푸터 include  -->
            <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        </div>
    </div>
    
    <script>
        const contextPath = "${contextPath}";
        const memberNo = "${loginMember.memberNo}";
    </script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="module" src="${contextPath}/resources/js/tetris/tetrisFunction.js" defer></script>
</body>
</html>