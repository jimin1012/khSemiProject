<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer>
	<div class="ft-img-wrap">
		<a href="${contextPath}"><img id="main-logo"
			src="${contextPath}/resources/images/main/image.png" alt="메뉴바img1"></a>
	</div>
	<div>
		<p>아무고토못하조(주) 사업자번호 : 123-12-12345 각자 대표이사: 김홍근, 유찬영, 신규성, 전지민
			호스팅서비스 사업자 : 아무고토못하조(주)</p>
		<p>이메일 주소 : yoo4young@gmail.com주소 : 강남지원 1관 : 서울특별시 강남구 테헤란로 14길 6
			남도빌딩 2F, 3F, 4F, 5F, 6F공정거래위원회 웹사이트(예정)</p>
		<p>Copyright © 2023-2024 Web site developed by 아무고토못하조</p>
	</div>
</footer>

<%-- session에 message 속성이 존재하는 경우 alert 창으로 해당 내용을 출력 --%>
<c:if test="${!empty sessionScope.message}">
	<script>
            alert("${sessionScope.message}");
			
            // EL 작성시 scope를 지정하지 않으면
            // page -> rquest -> session -> application 순서로 검색하여
            // 일치하는 속성이 있으면 출력
        </script>
	<!-- message 1회 출력 후 session에서 제거 -->

	<c:remove var="message" scope="session" />
</c:if>