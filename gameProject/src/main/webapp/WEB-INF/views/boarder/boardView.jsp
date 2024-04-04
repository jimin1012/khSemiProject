<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%-- map에 저장된 값을 각각 변수에 저장 --%>
<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />
<c:set var="boardLikeCount" value="${boardLikeCount}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세게시판</title>

<script src="https://kit.fontawesome.com/0ddb604158.js" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9cd918496e.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/board/boarderView.css">
<link rel="stylesheet" href="${contextPath}/resources/css/slide.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>


<body>
	<c:if test="${empty param.cp}">
		<c:set var="cp" value="1"/>
	</c:if>
	<c:if test="${!empty param.cp}">
		<c:set var="cp" value="${param.cp}"/>
	</c:if>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<!--                                     배경색 검정으로 지정 -->
		<div class="main-footer-wrap" >
			<!-- style="background-color: black;" -->
			<main>
				<jsp:include page="/WEB-INF/views/common/slide.jsp" />
				
				<!-- 여기에 구현하면 됨 -->
				<div class="boarderMain">
					<div class="boarderTitle">
						<!-- <div style="color : white">${view}</div> -->
						<div>${view.boardTitle}</div>
						<div>
							<div class="fnArea">
								<c:if test="${loginMember.memberNo == view.memberNo}">
									<div>
										<a href="delete?no=${param.no}&kind=${param.kind}" id="delete">게시글 삭제</a>
									</div>
									<div>|</div>
									<div>
										<a href="write?mode=update&boardNo=${param.no}&kind=${param.kind}&cp=${cp}" id="correction">게시글 수정</a>
									</div>
								</c:if>
							</div>
							<div>
								<div>2023년 12월 24일 금요일</div>
								<div>|</div>
								<div>${view.categoryName}</div>
							</div>
							<div id="boardLikeArea">
								<div id="boardLikeCount">
									<i class="fa-solid fa-thumbs-up" id="like"> ${boardLikeCount} </i>
									<!-- <i class="fa-regular fa-thumbs-up" id="like"> ${boardLikeCount}</i> -->
								</div>
							</div>
						</div>
					</div>
					<c:if test="${empty view.imageList}">
						<c:set var="end" value="${fn:length(view.imageList)}"/>
					</c:if>
					<c:if test="${!empty view.imageList}">
						<c:set var="end" value="${fn:length(view.imageList)-1}"/>
					</c:if>
					<div class="boarderDetail">
						<div>
							<c:if test="${!empty view.imageList}">
								<div class="boarderDetailImageArea">
									<c:forEach var="i" begin="0" end="${end}">
										
												<!-- <div><img src="https://image.ajunews.com//content/image/2021/01/21/20210121090154744011.jpg"></div>
												<div><img src="https://www.techm.kr/news/photo/202308/113983_142051_134.png"></div>
												<div><img src="https://cdn.econovill.com/news/photo/202206/578258_501740_2018.jpg"></div> -->
											<div><img src="${contextPath}${view.imageList[i].imagePath}"></div>
									
									</c:forEach>	
								</div>
							</c:if>
							<div class="textArea">${view.boardContent}</div>
						</div>
					</div>
					<div class="boarderReportArea">

						<c:if test="${param.kind == null}">
							<div id="callbackPage">뒤로가기</div>
						</c:if>
						<c:if test="${param.kind != null}">
							<div id="callback">목록으로</div>
						</c:if>
						<c:if test="${loginMember.memberNo != view.memberNo}">
							<div id="boarderReportBtn">게시글신고</div>
						</c:if>
						<div id="borderReport">
							<textarea id="reportText" cols="100" rows="6" style="overflow: auto;" placeholder="신고내역 작성"></textarea>
						</div>
						
						<div class="reportBtn" id="reportBtn">
							<button type="button" id="reportBtnClose">닫기</button>
							<button id="report">신고하기</button>
						</div>
					</div>

				<!-- 관리자용 게시글 신고 -->
				<c:if test="${loginMember.managerYn == 'Y' && view.reportConfirm != 0}">
					<div>
						<p style="color: crimson;">[신고된 게시글입니다.] <br>(아래 중 처리여부를 선택해주세요.)</p>
						<button id="deleteBoard">삭제</button>
						<button id="cancelBoard">반려</button>
					</div>
				</c:if>

				<jsp:include page="/WEB-INF/views/boarder/replyList.jsp" />

			</main>

			<jsp:include page="/WEB-INF/views/common/footer.jsp" />
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script>
		const contextPath = "${contextPath}"; // 최상위주소
		
        const boardNo = "${view.boardNo}"; // 게시글번호
		
        const loginMemberNo = "${loginMember.memberNo}"; // 로그인중인회원번호

		// const replyMemberProfile = "${replyList.memberProfile}"; // 댓글단 회원의 프로필 이미지
	</script>
	<script src="${contextPath}/resources/js/boarderView.js"></script>
	<script src="${contextPath}/resources/js/boardLike.js"></script>
	<script src="${contextPath}/resources/js/replyList.js"></script>
	<script src="${contextPath}/resources/js/reportpost.js"></script>

</body>

</html>