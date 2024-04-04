<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="main-banner">
	<div class="m-banner-img">
		<input type="radio" name="slide" id="slide01" checked>
		<input type="radio" name="slide" id="slide02">
		<input type="radio" name="slide" id="slide03">
		<%-- <input type="radio" name="slide"id="slide04"> --%>

		<ul class="slidelist">
			<li class="slideitem">
				<div>
					<div class="banner-con-box">
						<!-- <div class="banner-title">성장 동료 개발문화 근무환경</div>
                                        <div class="banner-con-1">시니어 개발자로</div>
                                        <div class="banner-con-2">우형에서 일한다면?</div>
                                        <div><a id="banner-link" href="#">알아보러 가기 ></a></div> -->
					</div>
					<a><img src="${contextPath}/resources/images/main/banner2.png"></a>
				</div>
			</li>

			<li class="slideitem">
				<div>
					<div class="banner-con-box">
						<!-- <div class="banner-title">리프레시 휴가, 단체상해보험, 도서비 지원 등등등</div>
                                        <div class="banner-con-1">우아한 형제들 복지를</div>
                                        <div class="banner-con-2">자세히 알려드려요</div>
                                        <div><a id="banner-link" href="#">입사하면 나도! ></a></div> -->
					</div>
					<a><img src="${contextPath}/resources/images/main/banner3.png"></a>
				</div>
			</li>
			<li class="slideitem">
				<div>
					<div class="banner-con-box">
						<!-- <div class="banner-title">구성원들의 솔직한 이야기</div>
                                        <div class="banner-con-1">우아한 형제들,</div>
                                        <div class="banner-con-2">정말 일하기 좋나요?</div>
                                        <div><a id="banner-link" href="#">확인하러 가기 > </a></div> -->
					</div>
					<a><img src="${contextPath}/resources/images/main/banner4.png"></a>
				</div>
			</li>

		</ul>
		<div class="label-box">
			<label for="slide01" class="label1"></label> <label for="slide02"
				class="label2"></label> <label for="slide03" class="label3"></label>
		</div>
	</div>
</section>