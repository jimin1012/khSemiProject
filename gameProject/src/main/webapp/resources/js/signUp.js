// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {
    "inputIdbox" : false,
    "inputPwbox" : false,
    "inputPw2box" : false,
    "inputNamebox" : false,
    "inputDobbox" : false,
    "inputEmailbox" : false,
    "inputEmailAuth" : false
};

const authBtn = document.getElementById("authBtn");
const inputEmailAuth = document.getElementById("inputEmailAuth");

/* 아이디 유효성 검사 */

const inputIdbox = document.getElementById("inputIdbox");
const idMessage = document.getElementById("idMessage");

inputIdbox.addEventListener("input", function(){

    if(inputIdbox.value.trim().length == 0){
        checkObj.inputIdbox = false;
        return;
    }
    const regExp = /^(?=.*\d)(?=.*[A-z])[\w]{6,15}$/;
    if(!regExp.test(inputIdbox.value)){
        checkObj.inputIdbox = false;
    }

})


/* 비밀번호 유효성 검사 */
const inputPwbox = document.getElementById("inputPwbox");
const inputPw2box = document.getElementById("inputPw2box");
const pwMessage = document.getElementById("pwMessage");
const pwMessage2 = document.getElementById("pwMessage2");

inputPwbox.addEventListener("input", function(){

    checkPw();

    if(inputPwbox.value.trim().length == 0 ){
        checkObj.inputPwbox = false;
        
        return;
    }

    const regExp = /^[\w!@#\-_+$%^&*]{6,15}$/; // 비밀번호 정규식

    if(regExp.test(inputPwbox.value)){

        checkObj.inputPwbox = true;

    }else{

        checkObj.inputPwbox = false;
    }
})

inputPw2box.addEventListener("input", checkPw);

function checkPw(){

    if(inputPw2box.value.trim().length == 0){
        checkObj.inputPw2box = false;
    }else{
        if(inputPwbox.value == inputPw2box.value){

            checkObj.inputPw2box = true;
        }else{
            
            checkObj.inputPw2box = false;
        }
    }

    
}



/* 이름 유효성 검사 */

const inputNamebox = document.getElementById("inputNamebox");
const nameMessage = document.getElementById("nameMessage");

inputNamebox.addEventListener("input", function(){

    if(inputNamebox.value.trim().length == 0){

        checkObj.inputNamebox = false;
        
        return;
    }
    
    const regExp = /^[가-힣]{2,5}$/;
    
    if(regExp.test(inputNamebox.value)){
        
        checkObj.inputNamebox = true;
    }else{

        checkObj.inputNamebox = false;
    }

    
})

/* 생년월일 유효성 검사 */

const inputDobbox = document.getElementById("inputDobbox");
const dobMessage = document.getElementById("dobMessage");

inputDobbox.addEventListener("input", function(){

    if(inputDobbox.value.trim().length ==0){
    checkObj.inputDobbox = false;
    return;
}


const regExp = /^(1[9]|2[0])\d{2}-(0[1-9]|1[0-2])-([0-2][1-9]|3[01])$/;

if(regExp.test(inputDobbox.value)){

    checkObj.inputDobbox = true;

    }else{

    checkObj.inputDobbox = false;
    }

});

/* 이메일 유효성 검사 */

const inputEmailbox = document.getElementById("inputEmailbox");

const mailSend = document.getElementById("mailSend");
mailSend.addEventListener("click",function(){
    if(inputEmailbox.value.trim().length ==0){
        alert("이메일을 입력하세요.");
        checkObj.inputEmailbox = false;
        return;
    }

    const regExp =/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if(regExp.test(inputEmailbox.value)){// 유효할 때
        checkObj.inputEmailbox = true; // 이메일 유효함
        inputEmailbox.readOnly = true; // input 읽기만 가능하게
        mailSend.disabled = true; // 버튼 못누르게
        mailSend.style.backgroundColor = "gray";
        authBtn.style.display = "block"; // 인증번호 버튼 보이게
        //유효한 이메일
        $.ajax({
            url: contextPath+"/mailSend",
            data : {"userEmail":inputEmailbox.value},
            type : "POST",
            success : function(data){
                authBtn.addEventListener("click",function(){
                    if(inputEmailAuth.value.trim().length == 0){
                        alert("인증번호를 입력하세요.");
                        checkObj.inputEmailAuth = false; // 인증번호 X
                        return;
                    }
                    if(data == inputEmailAuth.value.trim()){// 자바에서 만든 인증코드와 유저가 입력한 코드가 맞는지 확인
                        alert("이메일 인증 완료");
                        authBtn.disabled = true;
                        authBtn.style.backgroundColor = "gray";
                        inputEmailAuth.readOnly = true; 
                        checkObj.inputEmailAuth = true; // 인증번호 O
                    }else{
                        alert("인증번호가 일치하지 않습니다. 다시 입력해주세요.");
                        checkObj.inputEmailAuth = false;// 인증번호 X
                    }
                })
            },
            error : function(){
                console.log("이메일 오류");
                checkObj.inputEmailbox = false; // 이메일 X
            }
        })

    }else{
        alert("유효하지 않은 이메일 형식입니다.");
        checkObj.inputEmailbox = false; // 이메일 X
    }
})



// 아이디 중복체크
const idCheckBtn = document.getElementById("idCheck-btn");
idCheckBtn.addEventListener("click",function(){
    if(inputIdbox.value.trim().length == 0){
        alert("아이디를 입력하세요.");
        checkObj.inputIdbox = false;
        return;
    }
    const regExp = /^(?=.*\d)(?=.*[A-z])[\w]{6,15}$/;
    if(regExp.test(inputIdbox.value.trim())){ //유효할 때
        $.ajax({
            url : contextPath+"/DuplicateCheck",
            data : {"userId":inputIdbox.value},
            type : "GET",
            success : function(res){

                if(res>0){ // 이미 아이디가 있다면
                    alert("이미 저장되어 있는 아이디 입니다.");
                    checkObj.inputIdbox = false;
                }else{ // DB에 아이디가 없다면
                    alert("사용가능한 아이디입니다.");
                    inputIdbox.readOnly = true;
                    idCheckBtn.style.backgroundColor = "gray";
                    idCheckBtn.disabled = true;
                    checkObj.inputIdbox = true;
                    inputIdbox.style.backgroundColor = "#d3d3d3";
                }
            },
            error : function(){
                console.log("아이디 중복확인 오류");
                checkObj.inputIdbox = false;
            }
        })
       
    }else{
        alert("유효하지않은 아이디입니다.");
        checkObj.inputIdbox = false;
    }
});

function signUpValidate(){
    
    let str;
    for(let key in checkObj){
      
        if(!checkObj[key]){
            switch(key){
                case "inputIdbox"     : str = "아이디가" + " ";        break;
                case "inputPwbox"     : str = "비밀번호가" + " " ;      break;
                case "inputPw2box"    : str = "비밀번호 확인이" + " "; break;
                case "inputNamebox"   : str = "이름이" + " " ;        break;
                case "inputDobbox"    : str = "생년월일" + " ";      break;
                case "inputEmailbox"  : str="이메일이"+ " ";          break;
                case "inputEmailAuth" : str = "인증번호가" + " ";      break;
            }
            str += "유효하지 않습니다.";
            alert(str);
            document.getElementById(key).focus();
            return false; 
        }

    }

    return true;
    
}

