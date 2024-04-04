<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>랭킹 조회</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/rank.css">
    <script src="https://kit.fontawesome.com/0ddb604158.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
</head>
<body>
    <div class="wrap">
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <div class="main-footer-wrap">

            <main>
                <div class="container">
                    <h2 id="rankTitle">테트리스</h2>
                        <div class="rank-top">

                            <div class="rank-div"></div>

                            <div class="rank-div"></div>
                            
                            <div class="rank-div"></div>
                            
                        </div>
            
                        <div class="rank-main">
                            <div class="right">
                                <div class="searchBox">
                                    <input type="text" placeholder="이름으로 랭킹 조회" id="nameSearch" onkeyup="searchRank()">
                                    <select name="category" id="cate">
                                        <option value="1">테트리스</option>
                                        <option value="2">스네이크</option>
                                        <option value="3">달팽이</option>
                                        <option value="4">가위바위보</option>
                                        <option value="5">업다운</option>
                                        <option value="6">숫자야구</option>
                                    </select>
                                </div>
                            </div>

                            <div class="ranking"></div>
                        </div>
                </div>
            </main>


            <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        </div>
    </div>

    <div class="modal">
        <div>
            <div>
                <span class="modalTitle">검색된 랭킹</span> <br>


                <div class="modal-content">
                    <div>
                        <img src="/a-mu-go-to-mo-ta-jo/resources/images/프로필 기본값.png" class="trophy">
                    </div>
                    <div>
                        <div>유저047</div>
                        <div>48</div>
                    </div>
                    <div>4위</div>
                </div>


                <div class="modal-content">
                    <div>
                        <img src="/a-mu-go-to-mo-ta-jo/resources/images/프로필 기본값.png" class="trophy">
                    </div>
                    <div class="ranking-center">
                        <div>유저047</div>
                        <div>48</div>
                    </div>
                    <div>4위</div>
                </div>

            </div>
        </div>
        <span id="modal-close">&times;</span>
    </div>

    <script>
        const contextPath = "${contextPath}";
    </script>
    <script src="${contextPath}/resources/js/rank.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</body>
</html>