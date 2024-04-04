const clickId = document.getElementById("click-ID");
const clickPw = document.getElementById("click-PW");

const findInput1 = document.getElementById("findInput1");// 이름 또는 아이디
const findInput2 = document.getElementById("findInput2");// 생년월일

const findBtn = document.getElementById("find-btn"); // 찾기버튼
const text1 = document.querySelector("form > fieldset >p:first-child");

const idpwFindForm = document.getElementsByClassName("idpwFind-form");
const idResultWrap = document.getElementsByClassName("idResult-wrap");
const pwResultWrap = document.getElementsByClassName("pwResult-wrap");

const backBtn = document.getElementsByClassName("back");

/* 비밀번호 재수정 */
const userPw = document.getElementById("userPw"); // 새비밀번호
const userPwConfirm = document.getElementById("userPwConfirm");//새비밀번호 확인

let flag = true;

clickId.style.color="white";
clickId.style.backgroundColor="rgb(82, 131, 235)";
clickPw.style.backgroundColor="rgb(194, 194, 194)";



findBtn.addEventListener("click",function(){

	const regExp1  = /^[가-힣]{2,5}$/; //이름
	const regExp2 = /^(1[9]|2[0])\d{2}-(0[1-9]|1[0-2])-([0-2][1-9]|3[01])$/; // 생년월일
	const regExp3 = /^(?=.*\d)(?=.*[A-z])[\w]{6,15}$/; // 아이디
	
        
    if(flag==true){// 아이디 찾기 상태일 때
        /* 아이디 해당 하는 값이 있으면 여기로 이동 */
        
        if(findInput1.value.trim().length == 0 && findInput2.value.trim().length == 0){
            alert("이름 또는 생년월일을 입력하지 않았습니다.");
            }else if(!regExp1.test(findInput1.value.trim())){
                alert("유효하지 않은 이름 형식입니다.");
            }else if(!regExp2.test(findInput2.value.trim())){
                alert("유효하지 않은 생일 형식입니다.");
            }else{
            idpwFindForm[0].style.display="none";
            idResultWrap[0].style.display="flex";
            
            $.ajax({
                url : "idFind",
                data : {"memberName" : findInput1.value , "memberBirth" : findInput2.value},
                type : "GET",
                dataType : "JSON",
                success : function(res){
                    const idResultBox = document.getElementsByClassName("idResult-box")[0];
                   
                    idResultBox.innerText="";
                    // divBox.innerText="";
                    let divBox = document.createElement("div");
                    if(res.length > 0){
                        for (const i of res) {
                            divBox = document.createElement("div");
                            divBox.innerText = i.memberId.slice(0, -3) + '***';
                            divBox.classList.add("idResult");
                            idResultBox.append(divBox);
                        }
                    }else{
                        divBox.innerText = "정보와 일치하는 아이디가 없습니다.";
                        divBox.classList.add("idResult");
                        divBox.style.color= "red";
                        idResultBox.append(divBox);
                    }
                    findInput1.value="";
                    findInput2.value="";
                },
                error : function(){
                    console.log("에러발생");
                }
            });
            }
    }
    
    
    if(flag==false){ // 비밀번호 찾기 상태일 때

        if(findInput1.value.trim().length == 0 && findInput2.value.trim().length == 0){
            alert("아이디 또는 생년월일을 입력하지 않았습니다.");
        }else if(!regExp3.test(findInput1.value.trim())){
            alert("유효하지 않은 아이디입니다.");
        }else if(!regExp2.test(findInput2.value.trim())){
            alert("유효하지 않은 생일 형식입니다.");
        }else{
           
            $.ajax({
                url : "pwFind",
                data : {"memberId" : findInput1.value , "memberBirth" : findInput2.value},
                type : "GET",
                dataType : "JSON",
                success : function(res){

                    if(res>0){
                        idpwFindForm[0].style.display="none";
                        pwResultWrap[0].style.display="flex";
                    }else{
                        alert("일치하는 회원 정보가 없습니다.");
                    }
                    findInput1.value="";
                    findInput2.value="";
                },
                error : function(){
                    console.log("에러발생");
                }
            });
        }
      
    }
    
})

function changeValidate(){
    
    const regExp1 = /^[\w!@#\-_+$%^&*]{6,15}$/;// 비밀번호 정규식

    if(userPw.value.trim().length == 0){
        userPw.value = "";
        return printAlert(userPw,"새 비밀번호를 입력해주세요.");
    }else if(!regExp1.test(userPw.value.trim())){
        return printAlert(userPw,"영어, 숫자, 특수문자(!@#-_+$%^&*) 6~15글자 사이로 작성해주세요.");
    }

    if(userPwConfirm.value.trim().length == 0){
        userPwConfirm.value = "";
        return printAlert(userPwConfirm,"새 비밀번호 확인을 입력해주세요.");
    }else if(userPw.value.trim() != userPwConfirm.value.trim()){
        return printAlert(userPwConfirm,"새 비밀번호가 일치하지 않습니다.");
    }
    return true;
};

// 경고 출력 + 포커스 + false 반환 함수
function printAlert(el, message){// 매개변수 el은 요소
    alert(message);
    el.focus();

    return false;
}

// 상단 아이디 찾기 누르면
clickId.addEventListener("click",function(){
    idpwFindForm[0].style.display="flex";
    idResultWrap[0].style.display="none";
    pwResultWrap[0].style.display="none";
    clickId.style.color="white";
    clickId.style.backgroundColor="rgb(82, 131, 235)";
    clickPw.style.color="black";
    clickPw.style.backgroundColor="rgb(194, 194, 194)";
    findInput1.placeholder = "이름을 입력해주세요";
    text1.innerText = "이름";
    findBtn.innerText = "아이디 찾기";
    flag = true;
});

// 상단 비밀번호 찾기 누르면
clickPw.addEventListener("click",function(){
    idpwFindForm[0].style.display="flex";
    idResultWrap[0].style.display="none";
    pwResultWrap[0].style.display="none";
    clickPw.style.color="white";
    clickPw.style.backgroundColor="rgb(82, 131, 235)";
    clickId.style.color="black";
    clickId.style.backgroundColor="rgb(194, 194, 194)";
    findInput1.placeholder = "아이디를 입력해주세요";
    text1.innerText = "아이디";
    findBtn.innerText = "비밀번호 찾기";
    flag = false;
});

if(backBtn!=null){
    for (let i = 0; i < backBtn.length; i++) {
        backBtn[i].addEventListener("click",function(){
            const pathname= location.pathname;// 주소상에서 요청경로 반환

            // 이동할 주소 저장
            let url = pathname.substring(0,pathname.indexOf("/",1));
          

            url += "/login" 

            location.href = url;
        });
    }
}
