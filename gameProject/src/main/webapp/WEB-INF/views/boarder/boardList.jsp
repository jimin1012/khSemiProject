<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- map에 저장된 값을 각각 변수에 저장 --%>
<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판목록</title>

<script src="https://kit.fontawesome.com/0ddb604158.js"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9cd918496e.js"
	crossorigin="anonymous"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
	<link rel="icon" href="data:;base64,iVBORw0KGgo=">
	<link rel="stylesheet" href="${contextPath}/resources/css/board/borderlist.css">

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
					
					<form action="BoardList" method="get" id="boardSearch" onsubmit="return searchValidate()">
					
						<div class="searchArea">
							<div class="searchAreaCol1">
								<button><i class="fa-solid fa-magnifying-glass"></i></button>
							</div>
							<div class="searchAreaCol2">
								<input type="text" name="query" placeholder="검색할 게시글 (작성자 or 내용+제목)입력" id="searchQuery">
							</div>
							<div class="searchAreaCol3">
								<input type="hidden" name="kind" value="${param.kind}">
								<select name="key" id="searchKey">
									<option value="t">제목만</option>
									<option value="w">작성자</option>
									<option value="tc">내용+제목</option>
								</select>
							</div>
						</div>

					</form>

					<form action="#" class="nav" method="post">
						<div class="navigation">
							<div class="navCol1">
								<a href="${contextPath}/BoardList?kind=1">공지사항</a>
							</div>
							<div class="navCol2">
								<a href="${contextPath}/BoardList?kind=2">게임</a>
							</div>
							<div class="navCol3">
								<a href="${contextPath}/BoardList?kind=3">자유</a>
							</div>
							<div class="navCol4">
								<a href="${contextPath}/BoardList?kind=4">QnA</a>
							</div>
							<c:if test="${loginMember.managerYn == 'Y'}">
								<div class="navCol7">
									<a href="${contextPath}/report/post?cp=1">신고</a>
								</div>
							</c:if>
							<c:if test="${empty param.cp}">
								<!-- 파라미터에 cp가 없을경우 -->
								<c:set var="cp" value="1"/>
							</c:if>
							<c:if test="${!empty param.cp}">
								<!-- 파라미터에 cp가 없을경우 -->
								<c:set var="cp" value="${param.cp}"/>
							</c:if>
							<div class="newBoardArea">
								<a href="write?mode=insert&kind=${param.kind}&cp=${cp}" id="newBoardBtn">게시글 작성</a>
							</div>
						</div>
					</form>

					<div class="navList">
						<div>작성자</div>
						<div>게시글 제목</div>
						<div>게시글 내용</div>
					</div>

					<!-- 게시글 하나의 목차 -->
					<c:choose>

						<c:when test="${!empty boardList}">

							<c:forEach var="boardList" items="${boardList}">
								<div class="boarderlistArea">
									<a href="view?no=${boardList.boardNo}&cp=${pagination.currentPage}&kind=${param.kind}" id="linked">
										<div class="boarderlistAreaCol1Row2">
											<c:if test="${empty boardList.memberProfile}">
												<img src="https://w7.pngwing.com/pngs/36/333/png-transparent-profile-avatar-person-account-basic-user-interface-icon.png" id="profileImgWriter">
											</c:if>
											<c:if test="${!empty boardList.memberProfile}">
												<img src="${contextPath}${boardList.memberProfile}" id="profileImgWriter">
											</c:if>
											<div>${boardList.memberName} <!-- 작성자 --></div>
										</div>
										<div class="boarderlistAreaCol1Row1" id="boardTitle">
											${boardList.boardTitle}
										</div> <!-- 게시글 제목 -->
										<div class="boarderlistAreaCol1Row3">
											${boardList.boardContent}<!-- 게시글 내용 -->
										</div>
									</a>
								</div>
							</c:forEach>
							
						</c:when>

						<c:otherwise>
							<h4 style="color: white;">게시글이 존재하지 않습니다.</h4>
						</c:otherwise>

					</c:choose>


					<div class="selectBtnArea">
						
						<!-- 페이지네이션 a태그에 사용될 공통 주소를 저장할 변수 선언 -->
						<c:set var="url" value="BoardList?kind=${param.kind}&cp="></c:set>

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

	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="${contextPath}/resources/js/boarderList.js"></script>
	<script src="${contextPath}/resources/js/reportpost.js"></script>
</body>

</html>