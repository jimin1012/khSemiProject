
// // 댓글 목록 조회(AJAX)


// 댓글 목록 조회(AJAX)
let commentCurrentPage = 1;


(
    function(){
        selectReplyList(); 
    }

)()
function selectReplyList(){

    $.ajax({
        url : contextPath + "/reply/selectReplyList",
        data : {"boardNo" : boardNo , "commentCurrentPage" : commentCurrentPage},
        type : "GET",
        dataType : "JSON",
        success : function(replyList){

            const commentArea = document.getElementById("commentArea");
            commentArea.innerText = "";   
            
            for(let reply of replyList){

                const emptRow = document.createElement("div"); // 프로필 이미지 영역
                emptRow.classList.add()

                const profileImage = document.createElement("img");// 프로필 이미지
                if(reply.memberProfile == null){
                    profileImage.setAttribute("src" , "https://w7.pngwing.com/pngs/36/333/png-transparent-profile-avatar-person-account-basic-user-interface-icon.png");
                    profileImage.setAttribute("id" , "profile-image");
                }else{
                    profileImage.setAttribute("src" , contextPath+reply.memberProfile);
                    profileImage.setAttribute("id" , "profile-image");
                }
                
                const replyRow = document.createElement("div"); // 댓글 전체 영역
                replyRow.classList.add("replyRow");

                const replyWriter = document.createElement("div"); // 작성자명
                replyWriter.classList.add("replyWriter");
                replyWriter.innerText = reply.memberName;

                const replyContent = document.createElement("div"); // 댓글 내용
                replyContent.classList.add("replyContent");
                replyContent.innerHTML = reply.replyContent;

                const replyCreateArea = document.createElement("div"); // 작성 일자 + 삭제 버튼 영역
                replyCreateArea.classList.add("replyCreateArea");

                const createDateP = document.createElement("div"); // 작성일자 : 
                createDateP.classList.add("createDateP");

                const createDate = document.createElement("div"); // 작성된 일자
                createDate.classList.add("createDate");
                createDate.innerText = "(" + reply.replyDate + ")";

                const delBtn = document.createElement("a"); // 삭제 버튼(a태그)
                delBtn.classList.add("delBtn");
                if(reply.memberNo == loginMemberNo){
                    delBtn.innerText = "삭제";
                    delBtn.setAttribute("onclick" , "deleteReply(" + reply.replyNo + ")");
                }
                

                commentArea.append(emptRow, replyRow, replyContent);
                replyRow.append(profileImage, replyWriter, replyCreateArea);
                replyCreateArea.append(createDateP, createDate, delBtn);

            }

        },

        error : function(req, status, error){
            console.log("댓글 목록 조회(AJAX)_boardView.js 에러 발생");
            console.log("req.responseText");
        }

    })

}

// 댓글 더보기
// document.getElementById("commentMoreBtn").addEventListener("click" , function(){



// })

// 댓글 삭제 (AJAX)
function deleteReply(replyNo){

    if(confirm("삭제하시겠습니까?")){

        $.ajax({

            url : contextPath + "/reply/delete",
            data : {"replyNo" : replyNo},
            tpye : "GET",
            success : function(result){
                if(result > 0){
                    alert("댓글 삭제 완료");
                    selectReplyList();
                }else{
                    alert("댓글 삭제 실패");
                }
            },
            error : function(req, status, error){
                console.log("삭제(AJAX)_boardView.js 에러 발생");
                console.log("req.responseText");
            }

        })

    }

}

/* 댓글 작성 */
const comment = document.getElementById("comment");
const commentInputText = document.getElementById("commentInputText");

if(comment != null){
    comment.addEventListener("click",function(){

        if(commentInputText.value.trim().length == 0){
            alert("댓글을 작성한 후 버튼을 클릭하세요.");
            commentInputText.value="";
            commentInputText.focus();
            return;
        }

        $.ajax({
            url : contextPath+"/reply/insert",
            data : {"replyContent" : commentInputText.value, "boardNo" : boardNo, "loginMemberNo" : loginMemberNo},
            type : "POST",
            success : function(res){

                if(res>0){
                    alert("댓글이 등록되었습니다.");
                    commentInputText.value ="";
                    selectReplyList();
                }else{
                    alert("댓글 등록에 실패했습니다...");
                }
            },
            error : function(req, status,error){
                console.log("댓글 등록 실패");
                console.log(req.responseText);
            }
        });
    });
}

