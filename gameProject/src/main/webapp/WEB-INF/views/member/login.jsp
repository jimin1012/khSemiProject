<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
</head>
<body>
    <div class="wrap">
        <main>
            <div class="main-content">
                <div class="main-login">
                    <h2>로그인</h2>
                    <form action="login" name="login" class="login-form" method="post">
                        <fieldset>
                            <input type="text" name="memberId" id="memberId" placeholder="아이디" maxlength="30">
                            <input type="password" name="password" id="password" placeholder="비밀번호" maxlength="30">
                        </fieldset>
                        
                        <button id="login-btn" type="submit">로그인</button>
                    </form>
                </div>
                <div class="main-option">
                    <div>
                        <span>º 잠깐! 혹시 지원이 처음이신가요?</span>
                        <span><a href="agree">회원가입</a></span>
                    </div>
                    <div>
                        <span>º 로그인 정보를 잊어버리셨나요?</span>
                        <span><a href="idpwFind">아이디/비밀번호 찾기</a></span>
                    </div>
                </div>
            </div>
        </main>
        <!-- 푸터 include  -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
</body>
</html>