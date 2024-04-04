<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>가위바위보 게임</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/game/RSP.css">
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
                        <p id="ptxt1">졌습니다..ㅜㅜ</p>
                        <p id="ptxt2">게임 스코어를 저장하시겠습니까?</p>
                    </div>
                    <div>
                        <a id="save">예</a>
                        <a onclick=restart()>아니오</a>
                    </div>
                </div>

                <div class="popup-2">
                    <p id="result">이겼습니다.</p>
                    <button onclick=startGame()>확인(게임시작)</button>
                </div>

                <div class="score-container">
                    <p>점수</p>
                    <p id="score">0</p>
                </div>
                
                <div class="game-container">
                    <p>가위바위보 게임</p>
                    
                    <div class="game-main">
                        <div class="game-div">
                            <img src="${contextPath}/resources/images/game/rock.png" alt="가위, 바위, 보 이미지" id="player">
                        </div>

                        <div id="start" onclick="start()">게임 시작</div>

                        <div class="game-div">
                            <img src="${contextPath}/resources/images/game/rock.png" alt="가위, 바위, 보 이미지" id="computer">
                            
                        </div>

                    </div>

                    <div class="game-choose">

                        <div class="border-div">
                            <img src="${contextPath}/resources/images/game/scissors.png" alt="가위 이미지" id="scissors">
                        </div>

                        <div class="border-div">
                            <img src="${contextPath}/resources/images/game/rock.png" alt="바위 이미지" id="rock">
                        </div>

                        <div class="border-div">
                            <img src="${contextPath}/resources/images/game/page.png" alt="보자기 이미지" id="pager">
                        </div>

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
    <script src="${contextPath}/resources/js/RSP.js"></script>
</body>
</html>