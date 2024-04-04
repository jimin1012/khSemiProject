<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div>
		<a href="${contextPath}"><img id="main-logo" src="${contextPath}/resources/images/main/image.png"
			alt="메뉴바img1"></a>
	</div>

	<c:choose>
		<%-- 로그인이 되어있지 않은 경우--%>
		<c:when test="${empty sessionScope.loginMember}">
			<a href="${contextPath}/login" ><i class="fa-solid fa-user"></i><span>로그인</span></a>
			<a href="${contextPath}/gameList" ><i class="fa-solid fa-gamepad" ></i><span>게임목록</span></a>
			<a href="${contextPath}/BoardList?kind=1" ><i class="fa-solid fa-clipboard-list"></i><span>게시판</span></a>
			<a href="${contextPath}/rank" ><i class="fa-solid fa-ranking-star"></i><span>랭킹</span></a>
		</c:when>
		<%-- 관리자로그인이 되어있는 경우--%>
		<c:when test="${sessionScope.loginMember.managerYn == 'Y'}">
			<a href="${contextPath}/logout"><i class="fa-solid fa-arrow-right-from-bracket" ></i><span>로그아웃</span></a>
			<a href="${contextPath}/gameList"><i class="fa-solid fa-gamepad"></i><span>게임목록</span></a>
			<a href="${contextPath}/BoardList?kind=1"><i class="fa-solid fa-clipboard-list"></i><span>게시판</span></a>
			<a href="${contextPath}/rank"><i class="fa-solid fa-ranking-star"></i><span>랭킹</span></a>
			<a href="${contextPath}/myInfo"><i class="fa-regular fa-address-card"></i><span>마이페이지</span></a>
			<a href="${contextPath}/memberList"><i class="fa-solid fa-users"></i><span>회원목록</span></a>
		</c:when>
		<%-- 로그인이 되어있는 경우 --%>
        <c:otherwise>
        	<a href="${contextPath}/logout"><i class="fa-solid fa-arrow-right-from-bracket" ></i><span>로그아웃</span></a>
			<a href="${contextPath}/gameList"><i class="fa-solid fa-gamepad"></i><span>게임목록</span></a>
			<a href="${contextPath}/BoardList?kind=1"><i class="fa-solid fa-clipboard-list"></i><span>게시판</span></a>
			<a href="${contextPath}/rank"><i class="fa-solid fa-ranking-star"></i><span>랭킹</span></a>
			<a href="${contextPath}/myInfo"><i class="fa-regular fa-address-card"></i><span>마이페이지</span></a>
        </c:otherwise>
	</c:choose>

</header>