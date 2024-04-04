<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 댓글 -->
<div class="comment">댓글</div>
    
    <div class="commentAreaPart" id="commentAreaPart" >

        <div id="commentArea">
        <c:forEach var="reply" items="${replyList}">
            <div class="replyRow">
                <div class="profileImg">${loginMember.memberProfile}</div>
                <div class="replyWriter">${reply.memberName}</div>
                <div class="replyCreateArea">
                    <div class="createDateP"></div>
                    <div class="createDate">${reply.replyDate}</div>
                </div>
            </div>
            <div class="replyContent">${reply.replyContent}</div>
        </c:forEach>
        </div>

    </div>

    <!-- 댓글(작성) -->
    <div class="commentMoreArea">
        <div>
        </div>
    </div>

    <div class="commentWriterArea">
        <div>댓글작성</div>
        <div class="commentInput">
            <div>
                <textarea name="commentInputText" id="commentInputText" cols="200" rows="5" maxlength="1300"></textarea>
            </div>
            <div class="commentWriterBtn">
                <button type="button" id="comment">작성</button>
            </div>
        </div>
    </div>

</div>