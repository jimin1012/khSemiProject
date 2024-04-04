<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보 조회</title>
     <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <script src="https://kit.fontawesome.com/0ddb604158.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/my_info.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
</head>
<body>
    <div class="wrap">
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <div class="main-footer-wrap">

            <main>


                <div class="container">
                    
                    <div><h2>내 정보 조회</h2></div>
                    <form action="updateMyInfo" enctype="multipart/form-data" method="POST" onsubmit="">
                        <div class="myInfo">
                            <div>
                                <label>
                                    <span>이름</span> <br>
                                    <input type="text" value="${loginMember.memberName}" name="memberName" id="memberName" maxlength="6"> <br>
                                </label>
                                <label>
                                    <span>새 비밀번호</span> <br>
                                    <input type="password" name="memberNewPw" id="memberNewPw" placeholder="영어, 숫자, 특수문자 6~15글자" maxlength="30"> <br>
                                </label>
                                <label>
                                    <span>새 비밀번호 확인</span> <br>
                                    <input type="password" id="memberNewPw2" placeholder="다시 입력" maxlength="30"> <br>
                                </label>
                            </div>


                            <div class="profile img-label" >
                                <span class="delete-img">X</span>
                                <label for="my-profile">
                                    <c:if test="${empty loginMember.profileImg}">
                                        <c:set var="profileImg" value="/resources/images/프로필 기본값.png"/>
                                    </c:if>
                                    <c:if test="${!empty loginMember.profileImg}">
                                        <c:set var="profileImg" value="${loginMember.profileImg}"/>
                                    </c:if>
                                    <img src="${contextPath}${profileImg}" alt="클릭시 프로필 변경" id="profile-img">
                                    <input type="file" id="my-profile" name="profile" accept="image/*">
                                </label>
                            </div>


                        </div>
                        <div class="subInfo">
                            <button type="button" id="backBtn">뒤로가기</button>
                            <button type="button" id="cancellation-btn">회원 탈퇴</button>
                            <button type="button" id="updateBtn">수정 완료</button>
                        </div>
                        <input type="hidden" name="flag" value="1" id="flag">
                    </form>

                    <div><h2>게임별 최고 랭킹</h2></div>
                
                        <div class="scoreTable">

                        <c:if test="${empty score}">
                            <h1 style="color: white;">조회된 점수가 없습니다.</h1>
                        </c:if>

                        <c:if test="${!empty score}">

                            <table>
                                <thead>
                                    <tr>
                                        <th>GAME</th>
                                        <th>SCORE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="sc" items="${score}">
                                        <tr>
                                            <td class="memberName">${sc.gameName}</td>
                                            <td>${sc.score}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            
                        </c:if>
                            
                
                        </div>

                </div>
            </main>
            
            <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
            
        </div>
    </div>

    <div class="modal">
        <form action="cancellation" method="POST">
            <div>
                <span class="cancellation-span">비밀번호를 입력하세요.</span> <br>
                <input type="password" id="checkPw" name="checkPw" class="cancellation-input"  maxlength="30"> <br>
                <span class="cancellation-span">비밀번호를 한번 더 입력하세요.</span> <br>
                <input type="password" id="checkPw2" class="cancellation-input"  maxlength="30">
                <button type="button" id="subBtn">탈퇴</button>
            </div>
        </form>
        <span id="modal-close">&times;</span>
    </div>


    <script>
        const contextPath1 = "${contextPath}";
        const memberPw = "${loginMember.memberPw}";
        const loginMemberName = "${loginMember.memberName}";
    </script>
	<script src="${contextPath}/resources/js/myInfo.js"></script>
</body>
</html>