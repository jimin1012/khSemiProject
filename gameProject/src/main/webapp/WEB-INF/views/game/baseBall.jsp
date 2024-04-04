<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>숫자야구게임</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/game/baseBall.css">
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
                    <p>점수</p>
                    <p id="score">0</p>
                </div>
                <div class="game-container">
                    <p>숫자야구 게임</p>
                    
                    <div class="game-wrap">
                      
                    </div>
                    <input id="chatInput" type="text" size="40" onkeyup="inputEnter(event)" placeholder="4자리의 숫자를 입력해주세요(엔터 또는 버튼 클릭)" maxlength="10">
                    <button id="btn">입력!</button>
                </div>
            </main>
            <!-- 푸터 include  -->
            <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        </div>
    </div>
    <script>
        const contextPath = "${contextPath}";
        const memberNo = "${loginMember.memberNo}";
        const memberName = "${loginMember.memberName}";
        const profileImg = "${loginMember.profileImg}";
    </script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/baseBall/baseBall.js"></script>
</body>
</html>