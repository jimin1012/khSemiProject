<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="reportPostPagination" value="${map.reportPostPagination}" />
<c:set var="selectReportPostList" value="${map.selectReportPostList}" />
<c:set var="searchReportPostList" value="${map.searchReportPostList}" />


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>신고게시글목록</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script src="https://kit.fontawesome.com/0ddb604158.js"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9cd918496e.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${contextPath}/resources/css/board/reportpost.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/slide.css">
</head>

<body>

		<c:if test="${!empty param.key}">
            <c:set var="sURL" value="&query=${param.query}"></c:set>
        </c:if>

	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<div class="main-footer-wrap" style="background-color: black;">
			<!-- style="background-color: black;" -->
			<main>
				<jsp:include page="/WEB-INF/views/common/slide.jsp"/>
				<!-- 여기에 구현하면 됨 -->
				
				<form action="post" method="get" id="boardSearch" onsubmit="return searchValidate()">
				
					<div class="searchArea">
						<div class="searchAreaCol1">
							<button><i class="fa-solid fa-magnifying-glass"></i></button>
						</div>
						<div class="searchAreaCol2">
							<input type="text" name="query" placeholder=" 신고된 게시글(내용) 입력" id="searchQuery">
						</div>
						<div class="searchAreaCol3">
							<input type="hidden" name="cp" value="${param.cp}">
							<select name="key" id="searchKey">
								<option value="t">신고사유</option>
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
							<a href="${contextPath}/write?mode=insert&kind=1&cp=${cp}" id="newBoardBtn">게시글 작성</a>
						</div>
					</div>
				</form>
				<div class="navList">
					<div>신고된 게시글 제목</div>
					<div>신고된 게시글 내용</div>
					<div>신고 사유</div>
				</div>

				
				<c:if test="${empty searchReportPostList}">

					<c:choose>

							<c:when test="${!empty selectReportPostList}">
								<c:forEach var="selectReportPostList" items="${selectReportPostList}">
									<div class="detailArea" id="remove">
										<a href="${contextPath}/view?no=${selectReportPostList.boardNo}&cp=${reportPostPagination.currentPage}" id="linked">
											<div class="titleArea">
												<div class="titleBoard">
													<div>[ 게시글 번호 : ${selectReportPostList.boardNo} ]</div>
													<div>${selectReportPostList.boardTitle}</div>
													<div class="writer">
														<div>작성자 : ${selectReportPostList.memberName}</div>
													</div>
												</div>
												<div class="boardContentSelect">
													<div>${selectReportPostList.boardContent}</div>
												</div>
											</div>
										</a>
										<div class="reportArea">
											<div>${selectReportPostList.reportContent}</div>
										</div>
									</div>
								</c:forEach>
							</c:when>

							<c:otherwise>
								<h4 style="color: white;">게시글이 존재하지 않습니다.</h4>
							</c:otherwise>

					</c:choose>

				</c:if>
				<!-- <div>${searchReportPostList}</div> -->
				<c:if test="${!empty searchReportPostList}">

					<c:choose>

							<c:when test="${!empty searchReportPostList}">
								<c:forEach var="searchReportPostList" items="${searchReportPostList}">
									<div class="detailArea" id="remove">
										<a href="${contextPath}/view?no=${searchReportPostList.boardNo}&cp=${reportPostPagination.currentPage}" id="linked">
											<div class="titleArea">
												<div class="titleBoard">
													<div>[ 게시글 번호 : ${searchReportPostList.boardNo} ]</div>
													<div>${searchReportPostList.boardTitle}</div>
													<div class="writer">
														<div>작성자 : ${searchReportPostList.memberName}</div>
													</div>
												</div>
												<div class="boardContentSelect">
													<div>${searchReportPostList.boardContent}</div>
												</div>
											</div>
										</a>
										<div class="reportArea">
											<div>${searchReportPostList.reportContent}</div>
										</div>
									</div>
								</c:forEach>
							</c:when>

							<c:otherwise>
								<h4 style="color: white;">게시글이 존재하지 않습니다.</h4>
							</c:otherwise>

					</c:choose>

				</c:if>

				<button onclick="showMore()" id="showMore">더 보기</button>
			
				<!-- <div class="selectBtnArea">
					
					<c:set var="url" value="post?cp="></c:set>
					
					<fieldset id="pageClickArea">
						<a href="${url}${reportPostPagination.prevPage}${sURL}"><button id="pageClick">&lt;</button></a>

						<c:forEach var="page" begin="${reportPostPagination.startPage}" end="${reportPostPagination.endPage}" step="1">
							<a href="${url}${page}${sURL}"><button id="pageClick">${page}</button></a>
						</c:forEach>

						<a href="${url}${reportPostPagination.nextPage}${sURL}"><button id="pageClick">&gt;</button></a>
					</fieldset>

				</div> -->

			</main>

			<jsp:include page="/WEB-INF/views/common/footer.jsp" />
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script>
		const contextPath = "${contextPath}"; // 최상위주소
		
        const boardNo = "${view.boardNo}"; // 게시글번호
		
        const loginMemberNo = "${loginMember.memberNo}"; // 로그인중인회원번호
        
		const selectReportPostList = "${selectReportPostList}"; 
	</script>

	<script src="${contextPath}/resources/js/reportpost.js"></script>
</body>



</html>