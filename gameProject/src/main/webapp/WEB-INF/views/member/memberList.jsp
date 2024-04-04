<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- map에 저장된 값을 각각 변수에 저장 --%>
<c:set var="pagination" value="${map.pagination}" />
<c:set var="memberList" value="${map.memberList}" />
<c:if test="${empty param.kind}">
	<c:set var="param.kind" value="1"/>
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원목록</title>

<script src="https://kit.fontawesome.com/0ddb604158.js"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9cd918496e.js"
	crossorigin="anonymous"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
	<link rel="icon" href="data:;base64,iVBORw0KGgo=">
	<link rel="stylesheet" href="${contextPath}/resources/css/memberList.css">

	<link rel="stylesheet" href="${contextPath}/resources/css/slide.css">
</head>


<body>

	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<!-- 검색을 진행한 경우 key, query를 쿼리스트링 형태로 저장할 변수 생성 -->
        <c:if test="${!empty param.key}">
            <c:set var="sURL" value="&key=${param.key}&query=${param.query}"></c:set>
        </c:if>

		<div class="main-footer-wrap">
			<!-- 여기에 구현하면 됨 -->
			<div class="main-footer-wrap">
				<main>
					<jsp:include page="/WEB-INF/views/common/slide.jsp" />
					<!-- 여기에 구현하면 됨 -->
					
					<form action="memberList" method="get" id="boardSearch" onsubmit="return searchValidate()">
					
						<div class="searchArea">
							<div class="searchAreaCol1">
								<button><i class="fa-solid fa-magnifying-glass"></i></button>
							</div>
							<div class="searchAreaCol2">
								<input type="text" name="query" placeholder="조회할 회원의 이름 입력" id="searchQuery">
							</div>
						</div>

					</form>

                    <form action="#" class="nav" method="post">
						<div class="navigation">
							<div class="navCol1">
								<a href="${contextPath}/memberList?kind=1">전체 회원</a>
							</div>
							<div class="navCol2">
								<a href="${contextPath}/memberList?kind=2">관리자 요청 회원</a>
							</div>
							<c:if test="${empty param.cp}">
								<!-- 파라미터에 cp가 없을경우 -->
								<c:set var="cp" value="1"/>
							</c:if>
							<c:if test="${!empty param.cp}">
								<!-- 파라미터에 cp가 없을경우 -->
								<c:set var="cp" value="${param.cp}"/>
							</c:if>
							
						</div>
					</form>
                    

					<div class="navList">
						<div>회원 번호</div>
						<div>이름</div>
						<div>아이디</div>
						<div>탈퇴 여부</div>
						<div>생년월일</div>
					</div>

					<!-- 게시글 하나의 목차 -->
					<c:choose>

						<c:when test="${!empty memberList}">

							<c:forEach var="memberList" items="${memberList}">
								<div class="boarderlistArea">
										<div class="boarderlistAreaCol1Row2">
											<c:set var="profileImg" value="${loginMember.profileImg}"/>
											<img src="https://w7.pngwing.com/pngs/36/333/png-transparent-profile-avatar-person-account-basic-user-interface-icon.png" id="profileImgWriter">
											${memberList.memberNo} <!-- 회원 번호 -->
										</div>

										<!-- 회원 이름 -->
										<div class="boarderlistAreaCol1Row1" id="boardTitle">
											${memberList.memberName}
										</div> 

										<div class="boarderlistAreaCol1Row1" id="boardTitle">
											${memberList.memberId}
										</div> 

										<!-- 탈퇴 여부 -->
										<div class="boarderlistAreaCol1Row1">
											${memberList.statusYn}
										</div>

										<!-- 생년월일 -->
                                        <div class="boarderlistAreaCol1Row1">
											${memberList.memberBirthday}
										</div>

                                        <c:if test="${memberList.managerYn == 'R'}">
											<c:if test="${param.kind != 1}">
												<c:if test="${!empty param.kind}">
													<div class="request">
														관리자 요청을 수락하시겠습니까? <br>
														<a href="updateManager?no=${memberList.memberNo}&manager=Y" class="addManager">수락</a> <a href="updateManager?no=${memberList.memberNo}&manager=N" class="blockManager">거절</a>
													</div>
												</c:if>
											</c:if>
                                        </c:if>
								</div>
							</c:forEach>
							
						</c:when>

						<c:otherwise>
							<h4 style="color: white;">회원이 존재하지 않습니다.</h4>
						</c:otherwise>

					</c:choose>


					<div class="selectBtnArea">
						
						<!-- 페이지네이션 a태그에 사용될 공통 주소를 저장할 변수 선언 -->
						<c:set var="url" value="memberList?kind=${param.kind}&cp="></c:set>
						

						<fieldset id="pageClickArea">
							<a href="${url}${pagination.prevPage}${sURL}"><button id="pageClick">&lt;</button></a>

							<c:forEach var="page" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
								<a href="${url}${page}${sURL}"><button id="pageClick">${page}</button></a>
							</c:forEach>

							<a href="${url}${pagination.nextPage}${sURL}"><button id="pageClick">&gt;</button></a>
						</fieldset>

					</div>

				</main>

				<jsp:include page="/WEB-INF/views/common/footer.jsp" />
			</div>
		</div>

	<script>
		const k = "${param.kind}";
	</script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="${contextPath}/resources/js/memberList.js"></script>
	<script src="${contextPath}/resources/js/reportpost.js"></script>
</body>

</html>