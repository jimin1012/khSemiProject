<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>업다운게임</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/game/upDown.css">
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
                    <p>업다운 게임</p>
                    <div class="game-wrap">
                        <h1></h1>
                        <div class="img-wrap">
                            <img id="upDown" src="${contextPath}/resources/images/game/upDown/upDown.png" alt="upDown">
                        </div>
                        <h2>시도한 횟수 : </h2>
                        <input type="text" name="inputValue" id="inputValue" placeholder="숫자를 입력해주세요" maxlength="10">
                        <select name="scope" id="scope">
                            <option>원하는 숫자의 범위를 골라주세요(미선택시 기본 100)</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                            <option value="1000">1000</option>
                        </select>
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
    <script src="${contextPath}/resources/js/upDown/upDown.js"></script>
</body>
</html>