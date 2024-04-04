// 정규식
const regExp = /^[가-힣]{2,5}$/; // 이름 정규식
const regExp1 = /^[\w!@#\-_+$%^&*]{6,15}$/; // 비밀번호 정규식


const myProfile = document.getElementById("my-profile");

// 프로필 이미지 얻어오기
if(myProfile != null){

    myProfile.addEventListener("change", function(){

        if(this.files[0] != undefined){
            const reader = new FileReader();

            reader.readAsDataURL(this.files[0]);

            reader.onload = function(e){
                const profileImage = document.getElementById("profile-img");

                profileImage.setAttribute("src",e.target.result);
            }
        }else{
            profileImage.removeAttribute("src");
        }

    });

} 

// 프로필 이미지 삭제(기본 이미지로 되돌림)
const deleteImg = document.getElementsByClassName("delete-img")[0];

deleteImg.addEventListener("click", function(){
    const profileImage = document.getElementById("profile-img");
    const flag = document.getElementById("flag");

    flag.value = 0;

    profileImage.removeAttribute("src");
    profileImage.setAttribute("src", contextPath1 + "/resources/images/프로필 기본값.png")
    myProfile.value = "";
    
    // profileImage.src = contextPath
});

const modal = document.querySelector(".modal");
const modalClose = document.getElementById("modal-close");


// 이름, 새 비밀번호 정규식 체크
document.getElementById("updateBtn").addEventListener("click", function(){
    const memberNewPw = document.getElementById("memberNewPw");
    const memberNewPw2 = document.getElementById("memberNewPw2");
    const memberName = document.getElementById("memberName");
    let flag = false;

    if(memberName.value == loginMemberName){
        flag = true;
    }else{
        flag = checkId();
        memberName.focus();
    }
    
    if(flag){
        if(memberNewPw.value.trim().length == 0){
            // this.setAttribute("type", "submit");
            flag = true;
        }else{
            flag = checkPw();
            memberNewPw.focus();
        }
    }else{
        return;
    }
    

    if(flag){
        this.setAttribute("type", "submit");
    }
});

function checkPw(){
    if(regExp1.test(memberNewPw.value)){

        if(regExp1.test(memberNewPw2.value)){

            if(memberNewPw.value == memberNewPw2.value){
                return true;
            }else{
                alert("비밀번호를 동일하게 입력하세요.");    
                return false;
            }

        }else{
            alert("비밀번호 형식에 맞지 않습니다.");
            memberNewPw2.value = "";
            return false;
        }

    }else{
        alert("비밀번호 형식에 맞지 않습니다.");
        memberNewPw.value = "";
        return false;
    }
}

function checkId(){
    if(!regExp.test(memberName.value)){
        alert("입력된 이름이 형식에 맞지 않습니다.");
        return false;
    }else{
        return true;
    }
}



// modal 부분 (회원 탈퇴 버튼 클릭 시 나오는 부분)
(function(){

        // X버튼
        modalClose.addEventListener("click", function(){
            modal.classList.toggle("hide"); // hide 클래스 추가

            setTimeout(function(){ // 0.45초 후 동작

                modal.classList.toggle("hide"); // hide 클래스 제거

                modal.classList.toggle("show"); // remove

            }, 450);
        });
}());

document.getElementById("cancellation-btn").addEventListener("click", function(){
    modal.classList.toggle("show");
});

// 탈퇴버튼 제출 시 검사 사항
document.getElementById("subBtn").addEventListener("click", function(){

    const checkPw1 = document.getElementById("checkPw");
    const checkPw2 = document.getElementById("checkPw2");

    if(checkPw1.value == checkPw2.value){
        this.setAttribute("type", "submit");
    }else{
        alert("비밀번호가 일치하지 않습니다.");
        checkPw2.focus();
        this.setAttribute("type", "button");
    }
});

document.getElementById("backBtn").addEventListener("click", () => history.go(-1));