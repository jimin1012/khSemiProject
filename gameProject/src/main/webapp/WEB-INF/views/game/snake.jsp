<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스네이크게임</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/game/snake.css">
    <script src="https://kit.fontawesome.com/0ddb604158.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
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
                        <a id="save">예</a>
                        <a id="restart">아니오</a>
                    </div>
                </div>
                <div class="score-container">
                    <p class="score">점수 </p>
                    <p id="score">0</p>
                </div>
                <div class="game-container">
                    
                    <p>스네이크 게임</p>
                    <canvas id="game-board" width="400" height="400"></canvas>
                    <div class="buttons">
                      <button id="start-btn">시작</button>
                      <!-- <button id="stop-btn">정지</button> -->
                    </div>
                    
                  </div>
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
    <script src="${contextPath}/resources/js/snake/snake.js"></script>
</body>
</html>