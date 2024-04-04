const upDownImg = document.getElementById("upDown");
const inputValue = document.getElementById("inputValue");
const scope = document.getElementById("scope"); // 사용자가 선택한 범위

const score = document.getElementById("score"); //점수
const textH1 = document.getElementsByTagName("h1");
const textH2 = document.getElementsByTagName("h2");
const popup = document.getElementsByClassName("popup");
const save = document.getElementById("save");
const restart = document.getElementById("restart");
let scopeValue = Math.floor(Math.random()*100+1); // 기본 범위
let count = 0;


scope.addEventListener("change",function(){
    randomValue();
})

//유저가 선택할 게임값의 범위 선택안하면 기본 100
function randomValue(){
   
    if(scope.options[scope.selectedIndex].value == 50){
        let random = Math.floor(Math.random()*50+1);
        scopeValue = random;
    }else if(scope.options[scope.selectedIndex].value == 100){
        let random = Math.floor(Math.random()*100+1);
        scopeValue = random;
    }else if(scope.options[scope.selectedIndex].value == 1000){
        let random = Math.floor(Math.random()*1000+1);
        scopeValue = random;
    }else{
        let random = Math.floor(Math.random()*100+1);
        scopeValue = random;
    }
}


inputValue.addEventListener("keyup",function(){

    regExp = /^[\d]*$/;
    if(event.key=="Enter"){ //눌러진 key가 Enter인 경우
        if(inputValue.value.trim().length == 0 || !regExp.test(inputValue.value.trim())){
           alert("숫자를 입력해주세요.");
           inputValue.focus();
        }else{
            textH2[0].innerText = "시도한 횟수 : "+ ++count;
        
            if(inputValue.value == scopeValue){ // 정답인 경우
                textH1[0].innerText = "정답!";
                score.innerText = count;
                upDownImg.src = "../resources/images/game/upDown/upDown.png";
                //바로 팝업 띄워서 기록 저장해야함 왜냐하면 이건 시도 횟수 적은것이 높은 점수
                popup[0].style.display="block";
            }else if(inputValue.value > scopeValue){
                textH1[0].innerText = "숫자가 더 낮습니다";
                upDownImg.src = "../resources/images/game/upDown/up.png";
            }else if(inputValue.value < scopeValue){
                textH1[0].innerText = "숫자가 더 높습니다";
                upDownImg.src = "../resources/images/game/upDown/down.png";
            }
        }
        
        inputValue.value = "";
    }
    
})

save.addEventListener("click",function(){
    let flag = false;
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"memberNo" : memberNo,"gameNo" : 5},
        type : "POST",
        dataType : "JSON",
        success: (res)=>{

           if(res == "-1"){
                saveScoreEvent();
           }else{
                for (const i of res) {
                    if(i == score.innerText){
                        flag = true;
                    }
                }
                if(flag == true){
                    if(confirm("이미 저장 되어있는 점수 입니다. \n 게임을 다시 시작하시겠습니까?")){
                        gameReStart();
                    }else{
                        location.href = contextPath;
                    }
                }else{
                    saveScoreEvent();
                }
           }

      
        },
        error : function(){
            console.log("에러발생");
        }
    });
   

    
});

restart.addEventListener("click",function(){
    gameReStart();
})

function gameReStart(){
    popup[0].style.display = "none";
    textH1[0].innerText = "";
    textH2[0].innerText = "시도한 횟수 : ";
    count = 0;
    // scopeValue = 100;
    randomValue();
    score.innerText = 0;
    inputValue.value="";
    scope.options[0].selected = true;
}

function saveScoreEvent(){
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"score" : score.innerText,"gameNo" : 5},
        type : "GET",
        success: (res)=>{
            if(res>0){
                alert("점수가 정상적으로 저장되었습니다!");
                gameReStart();
            }else{
                alert("점수가 정상적으로 저장되지 않았습니다!");
            }
            
        },
        error : function(){
            console.log("에러발생");
        }
    });
}