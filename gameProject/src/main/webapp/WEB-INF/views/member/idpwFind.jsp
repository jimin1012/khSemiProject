<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디/비밀번호찾기</title>
<link rel="stylesheet" href="${contextPath}/resources/css/idpwFind.css">
<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
</head>
<body>
	<div class="wrap">
		<main>
			<div class="main-content">
				<div class="main-idpwFind">
					<div class="idpw-choice">
						<a id="click-ID">아이디 찾기</a> <a id="click-PW">비밀번호 찾기</a>
					</div>
					<form action="#" name="idpwFind" class="idpwFind-form" method="post">
						<fieldset>
							<p>이름</p>
							<input type="text" name="findInput1" id="findInput1"
								placeholder="이름을 입력해주세요(한글 2~5글자)" maxlength="30">
							<p>생년월일</p>
							<input type="text" name="findInput2" id="findInput2"
								placeholder="YYYY-MM-DD" maxlength="15">
						</fieldset>

						<div class="btn-wrap">
							<a class="back" href="#">돌아가기</a>
							<button id="find-btn" type="button">아이디 찾기</button>
						</div>

					</form>
					<div class="idResult-wrap">
						<p>해당하는 아이디</p>
						<div class="idResult-box">
							
						</div>
						<a href="#" class="back">돌아가기</a>
					</div>
					<div class="pwResult-wrap">
						<form action="pwFind" method="post" name="pwReset"  onsubmit="return changeValidate()">
							<div>
								<p>새 비밀번호</p>
								<input type="password" name="userPw" id="userPw"
									placeholder="영어,숫자,특수문자(!@#-_+$%^&*)6~15글자로 작성" maxlength="30">
							</div>
							<div>
								<p>새 비밀번호 확인</p>
								<input type="password" id="userPwConfirm"
									placeholder="위 비밀번호를 다시 입력해주세요" maxlength="30">
							</div>
							<div class="btn-wrap">
								<button id="pwReset-btn">비밀번호 재설정</button>
								<a href="#" class="back">돌아가기</a>
							</div>
						</form>
					</div>
				</div>

			</div>
		</main>
		<!-- 푸터 include  -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<%-- jQuery 라이브러리 추가 --%>
   <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/idpwFind.js"></script>
</body>
</html>