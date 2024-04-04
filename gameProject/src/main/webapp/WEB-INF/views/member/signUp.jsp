<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link rel="stylesheet"	href="${contextPath}/resources/css/board/signUp.css">
 <link rel="stylesheet" href="${contextPath}/resources/css/font.css">

<title>회원가입</title>
</head>
<body>
	<div class="wrap">
		<main>
			<form action="signUp" method="POST" name="singUp-form" onsubmit="return signUpValidate()">
				<section class="signUp-area">

					<p>회원가입</p>

					<div class="main">

						<table>
							<tr>
								<th>아이디</th>
								<td style="width: 250px;">
									<input type="text" id="inputIdbox" name="inputIdbox"
									placeholder="6글자~15글자 (숫자와 영어만 가능)" style="width: 100%;" maxlength="30">
								</td>
								<td>
									<button type="button" id="idCheck-btn">중복 확인</button>
								</td>
							</tr>


							<!-- 비밀번호 -->
							<tr>
								<th>비밀번호</th>
								<td>
									<input type="password" id="inputPwbox" name="inputPwbox"
									placeholder="6글자~15글자 (공백,한글)제외" maxlength="30">
								</td>
							</tr>

							<!-- 비밀번호 확인 -->
							<tr>
								<th>비밀번호 확인</th>
								<td>
									<input type="password" id="inputPw2box" name="inputPw2box"
									placeholder="위 비밀번호를 다시 입력해주세요." maxlength="30">
								</td>
							</tr>

							
							<!-- 이름 -->
							<tr>
								<th>이름</th>
								<td>
									<input type="text" id="inputNamebox" name="inputNamebox"
									placeholder="2~5글자, 한글만 입력" maxlength="6">
								</td>
							</tr>

							<!-- 생년월일 -->
							<tr>
								<th>생년월일</th>
								<td>
									<input type="text" id="inputDobbox" name="inputDobbox"
									placeholder="ex) YYYY-MM-DD" maxlength="15">
								</td>
							</tr>
							

							<!-- 이메일 -->
							<tr>
								<th>이메일</th>
								<td>
									<input type="text" id="inputEmailbox" name="inputEmailbox" placeholder="이메일을 입력하세요." maxlength="40">
								</td>
								<td>
									<button type="button" id="mailSend">인증번호 받기</button>
								</td>
							</tr>
							<tr>
								<td colspan="1"></td>
								<td><input type="text" id="inputEmailAuth" name="inputEmailAuth" placeholder="인증번호를 입력하세요." maxlength="10"></td>
								<td><button type="button" id="authBtn">인증 확인</button></td>
							</tr>
							

						</table>


						<button id="signUpbtn" name="signUpbtn">회원가입</button>
					</div>


				</section>
				<input type="hidden" value="${param.manager}" name="managerYN">
			</form>


		</main>
		<!-- 푸터 include  -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<script>
		const contextPath = "${contextPath}";

	</script>
	

	<%-- jQuery 라이브러리 추가 --%>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/signUp.js"></script>

</body>
</html>