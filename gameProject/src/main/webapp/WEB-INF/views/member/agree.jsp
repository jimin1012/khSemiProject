<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>약관동의</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board/agree.css">
	 <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
</head>
<body>
	<div class="wrap">
		<main>
		    <section class="agree-area">
		        <form action="signUp"  name="agreeform" >
		        
		            <p id="title">약 관 동 의</p>
		
		            <label class="checkInput"><input type="checkbox" id="tosCheck" name="tosCheck">이용약관 동의(필수)</label>
		            <div class="box1">
		                <h3>제1조(목적)</h3>
		                <P>이 약관은 (유)내고향시푸드(전자거래 사업자)이 운영하는 홈페이지(이하 "쇼핑몰"이라 한다)에서 제공하는 인터넷 관련 서비스(이하 "서비스"라 한다)를 이용함에 있어 (유)내고향시푸드와 이용자의 권리·의무 및 책임사항을 규정함을 목적으로 합니다. </p>
		                    ※ 「PC통신 등을 이용하는 전자거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다」 </P>
		            </div>
		
		            <label class="checkInput"><input type="checkbox" id="marketingCheck" name="marketingCheck">마케팅 동의(선택)</label>
		            <div class="box2">
		                <h3>제1조(목적)</h3>
		                <P>이 약관은 (유)내고향시푸드(전자거래 사업자)이 운영하는 홈페이지(이하 "쇼핑몰"이라 한다)에서 제공하는 인터넷 관련 서비스(이하 "서비스"라 한다)를 이용함에 있어 (유)내고향시푸드와 이용자의 권리·의무 및 책임사항을 규정함을 목적으로 합니다. </p>
		                    ※ 「PC통신 등을 이용하는 전자거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다」 </P>
		            </div>
		
		            <label class="checkInput"><input type="checkbox" id="allCheck" name="allCheck">이용약관 정보 수집 및 전체동의</label>
		            <label class="checkInput"><input type="checkbox" id="manager" name="manager">관리자 가입으로 요청</label>
		
		            <div class="btn">
		                <button type="submit" id="nextbtn">다 음</button>
		                <a href = " ${contextPath}">취 소</a>
		            </div>
		        </form>
		    </section>
    
    </main>
		<!-- 푸터 include  -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	
	 <script src="${contextPath}/resources/js/agree.js"></script>
</body>
</html>