<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board/boardWrite.css">
    
    <script src="https://kit.fontawesome.com/0ddb604158.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/slide.css">
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
                        <c:if test="${param.mode =='insert'}">
                            <h2>게시글 작성</h2>
                        </c:if>
                        <c:if test="${param.mode =='update'}">
                            <h2>게시글 수정</h2>
                        </c:if>
                    </div>
                    <!-- 게시글 작성 몸통 부분 -->
                    <form action="write" method="post" name="boardWrite" enctype="multipart/form-data" onsubmit="return writeValidate()">
                        <div class="input-area">
                            <label for="boardTitle" >제목</label>
                            <input type="text" name="boardTitle" id="boardTitle" placeholder="게시글 제목" value="${view.boardTitle}" maxlength="19">
                        </div>
                        <div class="input-area">
                            <label for="boardTitle">카테고리</label>
                            <select name="boardCategory" id="boardCategory">
                                <option value="0">카테고리 선택</option>
                                <c:if test="${loginMember.managerYn == 'Y'}">
                                       <option value="1">공지</option>
                                </c:if>
                                <option value="2">게임</option>
                                <option value="3">자유</option>
                                <option value="4">QnA</option>
                            </select>
                        </div>
                   		<c:forEach var="boardImage" items="${view.imageList}" varStatus="var">
                            <c:choose>
                                <c:when test="${var.index == 0}">
                                    <c:set var="img0" value="${contextPath}${boardImage.imagePath}"/>
                                    <c:set var="bNo0" value="${boardImage.imageNo}"/>
                                </c:when>
                                <c:when test="${var.index == 1}">
                                    <c:set var="img1" value="${contextPath}${boardImage.imagePath}"/>
                                    <c:set var="bNo1" value="${boardImage.imageNo}"/>
                                </c:when>
                                <c:when test="${var.index == 2}">
                                    <c:set var="img2" value="${contextPath}${boardImage.imagePath}"/>
                                    <c:set var="bNo2" value="${boardImage.imageNo}"/>
                                </c:when>
                            </c:choose>
                           
                        </c:forEach> 
                        
                        <div class="input-area img-wrap">
                            <div class="img-box">
                                <label for="img1">
                                    <img class="preview" src="${img0}">
                                </label>
                                <input type="file" name="1" id="img1" class="inputImage" accept="image/*">
                                <span class="delete-image">&times;</span>
                                <!-- &times; : x모양 문자 -->
                            </div>
                            <div class="img-box">
                                <label for="img2">
                                    <img class="preview" src="${img1}">
                                </label>
                                <input type="file" name="2" id="img2" class="inputImage" accept="image/*">
                                <span class="delete-image">&times;</span>
                                <!-- &times; : x모양 문자 -->
                            </div>
                            <div class="img-box">
                                <label for="img3">
                                    <img class="preview" src="${img2}">
                                </label>
                                <input type="file" name="3" id="img3" class="inputImage" accept="image/*">
                                <span class="delete-image">&times;</span>
                                <!-- &times; : x모양 문자 -->
                            </div>
                        </div> 
                        <div class="input-area textArea">
                            <label for="boardContent">내용</label>
                            <textarea name="boardContent" id="boardContent" cols="30" rows="10" maxlength="1300">${view.boardContent}</textarea>
                        </div>
                        <div class="btn-wrap">
                            <a href="${header.referer}" id="cancle">취소</a>
                            <c:if test="${param.mode == 'insert'}">
                                <button type="submit" id="writeBtn">게시글 작성</button>
                            </c:if>
                            <c:if test="${param.mode == 'update'}">
                                <button type="submit" id="writeBtn">게시글 수정</button>
                            </c:if>
                        </div>    
                        <input type="hidden" name="mode" value="${param.mode}">
                        <!-- 게시판 구분 -->
                        <input type="hidden" name="kind" value="${param.kind}">

                        <!-- 현재페이지 -->
                        <input type="hidden" name="cp" value="${param.cp}">

                        <!-- 게시판 번호 -->
                        <input type="hidden" name="boardNo" value="${param.boardNo}">
                        <input type="hidden" name="deleteList" id="deleteList" value="">                    
                    </form>

                </section>
            </main>
            <!-- 푸터 include  -->
            <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        </div>
    </div>
    <script>
    	const bNo0 = "${bNo0}";
    	const bNo1 = "${bNo1}";
    	const bNo2 = "${bNo2}";
    </script>
    <script src="${contextPath}/resources/js/board/boardWrite.js"></script>
</body>
</html>