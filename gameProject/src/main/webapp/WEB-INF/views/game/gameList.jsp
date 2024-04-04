<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Game List</title>

<link rel="stylesheet" href="${contextPath}/resources/css/game/gamelist.css">
<script src="https://kit.fontawesome.com/9cd918496e.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/slide.css">
<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
</head>

<body style="color: white;">

	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<!--                                     배경색 검정으로 지정 -->
		<div class="main-footer-wrap" style="background-color: black;">
			<!-- style="background-color: black;" -->
			<main>
				<jsp:include page="/WEB-INF/views/common/slide.jsp" />
				<!-- 여기에 구현하면 됨 -->

				<!-- <div class="inputArea">
					<div>
						<i class="fa-solid fa-magnifying-glass"></i>
					</div>
					<div>
						<input type="text" placeholder="  제목으로 게임을 검색해주세요!">
					</div>
					<div>
						<button type="submit">검색</button>
					</div>
				</div> -->


				<c:set var="tetris" value="tetris" />
				<c:set var="snake" value="snake" />
				<c:set var="rsp" value="rsp" />
				<c:set var="upDown" value="upDown" />
				<c:set var="baseball" value="baseball" />
				<c:set var="snail" value="snail" />

				<c:forEach var="gameList" items="${gameList}">
					
					<form action="#" method="post" name="myPage-form" enctype="multipart/form-data" onsubmit="return gameImageValidate()">
						<div class="gamelist">
							<div><img src="${gameList.gameImg}"></div>
							<div>
								<p>${gameList.gameName}</p>
								<c:choose>
									<c:when test="${gameList.gameNo == 1}">
										<div>1985년 세상에 등장한 명작</div>
									</c:when>
									<c:when test="${gameList.gameNo == 2}">
										<div>매우 간단한 스네이크 게임</div>
									</c:when>
									<c:when test="${gameList.gameNo == 3}">
										<div>어디서 많이 본 거 같지만 기분 탓인 달팽이 게임</div>
									</c:when>
									<c:when test="${gameList.gameNo == 4}">
										<div>컴퓨터와 함께하는 두근두근♥ 가위바위보 게임</div>
									</c:when>
									<c:when test="${gameList.gameNo == 5}">
										<div>위 아래 위위 아래</div>
									</c:when>
									<c:when test="${gameList.gameNo == 6}">
										<div>제가 1994년 LA에 있었을 때...</div>
									</c:when>
								</c:choose>
								
							</div>
							<c:if test="${gameList.gameNo == 1}"><div><a href="game/tetris">Game Start!</a></div></c:if>
							<c:if test="${gameList.gameNo == 2}"><div><a href="game/snake">Game Start!</a></div></c:if>
							<c:if test="${gameList.gameNo == 3}"><div><a href="game/snail">Game Start!</a></div></c:if>
							<c:if test="${gameList.gameNo == 4}"><div><a href="game/rsp">Game Start!</a></div></c:if>
							<c:if test="${gameList.gameNo == 5}"><div><a href="game/upDown">Game Start!</a></div></c:if>
							<c:if test="${gameList.gameNo == 6}"><div><a href="game/baseball">Game Start!</a></div></c:if>
							

							<!-- 관리자로 로그인할때만 보이게 설정 -->
							<%-- <c:if test="${loginMember.managerYn == 'Y'}">
								<div class="profile-btn-area">
									<label for="input-gameImage" style="color: white;">이미지 선택</label>
									<input type="file" name="gameImage" id="input-gameImage" accept="gameImage/*">
									<button type="submit">변경하기</button>
								</div>
							</c:if> --%>
						</div>
					</form>
					
				</c:forEach>

			</main>

			<jsp:include page="/WEB-INF/views/common/footer.jsp" />
		</div>
	</div>

</body>

</html>