<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>gameProject</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">
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
                <!-- slide include -->
                <jsp:include page="/WEB-INF/views/common/slide.jsp"/>
                <section class="main-wrap">
                    <div class="main-title">
                        <h2>사람들과 게임 점수를 경쟁해보세요!</h2>
                    </div>
                    <div class="main-content">
                        <div class="m-con-top">
                            <div class="con-box">
                                <div class="img-wrap">
                                    <a href="game/tetris"><img src="https://png.pngtree.com/thumb_back/fw800/background/20230527/pngtree-tetris-wallpaper-hd-1080p-image_2693812.jpg" alt=""></a>
                                </div>
                                <p class="con-box-title">추억의 게임 테트리스는 어때요?</p>
                                <p class="con-box-text">1985년 세상에 등장한 명작</p>
                            </div>
                            <div class="con-box">
                                <div class="img-wrap">
                                    <a href="game/snake"><img src="https://play-lh.googleusercontent.com/L9opLtUUqK0yOTq7uXTou1B4jyqf5Z_kTIG8CShM6tpsXLMTEjg5GVDzcnAO7GxOk9w7" alt=""></a>
                                </div>
                                <p class="con-box-title">벽에 닿지말고 최대한 길게 몸을 늘려보세요!</p>
                                <p class="con-box-text">매우 간단한 스네이크 게임</p>
                            </div>
                            <div class="con-box">
                                <div class="img-wrap">
                                    <a href="game/rsp"><img src="https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/6Opp/image/nJtTUmhg2apMuSs8mJPK18enXEc.jpg" alt=""></a>
                                </div>
                                <p class="con-box-title">컴퓨터와 함께하는 두근두근♥ 가위바위보 게임</p>
                                <p class="con-box-text">쉽게 이길 수 있을까요?</p>
                            </div>
                        </div>
                        <div class="m-con-bottom">
                            <div class="con-box">
                                <div class="img-wrap">
                                    <a href="game/upDown"><img src="https://upup.fm/images/logo.png" alt=""></a>
                                </div>
                                <p class="con-box-title">위 ↑아래↓ 위위↑↑ 아래↓</p>
                                <p class="con-box-text">UPDOWN GAME!</p>
                            </div>                        
                            <div class="con-box">
                                <div class="img-wrap">
                                    <a href="game/baseball"><img src="https://static.indischool.com/images/users/65b5fe18ba01c21a7fd634d337a5d361/739321bf-37b9-49f8-8a2e-32d3aa6010c3.png" alt=""></a>
                                </div>
                                <p class="con-box-title">과연 몇번의 기회 안에 맞출 수 있을까요?</p>
                                <p class="con-box-text">제가 1994년 LA에 있었을 때...</p>
                            </div>                        
                            <div class="con-box">
                                <div class="img-wrap">
                                    <a href="game/snail"><img src="https://image.cine21.com/resize/cine21/article/2013/0722/16_59_46__51ece6723e220[S800,800].jpg" alt=""></a>
                                </div>
                                <p class="con-box-title">어디서 많이 본 거 같지만 기분 탓인 달팽이 게임</p>
                                <p class="con-box-text">달팽이 게임</p>
                            </div>                        
                        </div>
                    </div>
                </section>
            </main>
            <!-- 푸터 include  -->
            <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
           
        </div>
    </div>
</body>
</html>