const bg = document.getElementsByClassName("game-wrap");
const input = document.getElementById("chatInput");
const btn = document.getElementById("btn");
const score =document.getElementById("score");
/* 팝업 관련 */
const save = document.getElementById("save");
const restart = document.getElementById("restart");
const popup = document.getElementsByClassName("popup");

let regExp = /^\d{4}$/; //입력 값 정규식
let arr = [];
let strike = 0;
let ball = 0;

let count = 0;
let  numberValue = 0;


// 랜덤 값 처음에 바로 삽입

(
    ranArr
)()

function ranArr(){
    while(arr.length < 4){
        numberValue = parseInt(Math.random()*9+1);
    
        if(arr.indexOf(numberValue) == -1){//중복 x
            arr.push(numberValue);
        }
    }
}


function readValue(){
   
    strike = 0;
    ball = 0;
 
   
    if(input.value.trim().length > 0){

        if(regExp.test(input.value)){

            count  += 1;

            //유저가 입력한 값 배열에 담음
            valueArr = Array.from(String(input.value), Number);
            

            bg[0].innerHTML += "<div class='chat ch2'> <div class='textbox'> "+input.value.trim()+"</div></div>";
            // bg[0].scrollTop = bg[0].scrollHeight;
            input.value ="";
            input.focus();

            for(let i =0; i<4; i++){
                // 답 배열과 유저가 입력한 배열 비교
                if(arr[i] == valueArr[i]){
                    strike += 1;
                }
                for (let j = 0; j < 4; j++) {
                    if(arr[i] == valueArr[j] && arr[i] != valueArr[i] && arr[j] != valueArr[j]){
                        ball += 1;
                    }
                }
            }

            bg[0].innerHTML += "<div class='chat ch1'><div class='icon'><img class='fa-solid fa-user' src='"+contextPath+profileImg+"' alt=''></div><div class='textbox-wrap'><div class='name'>"+memberName+"</div><div class='textbox'>"+ strike+"스트라이크 "+ball+"볼 " +" </div></div></div>";
            bg[0].scrollTop = bg[0].scrollHeight;
        
            if(strike == 4){ // 스트라이크가 4가 되었을 때 지금까지 시도한 횟수가 점수가 됨
                popup[0].style.display="block";
               
                score.innerText= count;
            }
        }else{
            alert("숫자가 아니거나 입력 길이가 맞지않습니다.")
        }
       
    }
}

save.addEventListener("click",function(){
    let flag = false;
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"memberNo" : memberNo,"gameNo" : 6}, // 숫자야구 게임 넘버 6
        type : "POST",
        dataType : "JSON",
        success: (res)=>{
        
           // DB에 저장되어 있는 값이 아예 없다면 비교할 Score가 없으므로 바로 저장
           if(res == "-1"){
                saveScoreEvent();
           }else{
                for (const i of res) {
                    // DB에 이미 있는 값과 지금 유저의 값이 같다면 true
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
})

restart.addEventListener("click",function(){
    gameReStart();
})


btn.addEventListener("click",function(){
    readValue();
})

function inputEnter(event){
    if(event.key=="Enter"){ //눌러진 key가 Enter인 경우
        readValue();//함수 호출
    }
}

function gameReStart(){
    popup[0].style.display = "none";
    count = 0;
    score.innerText = 0;
    input.value="";
    bg[0].innerHTML = "";

    numberValue = 0;
    arr.length = 0
    ranArr();

}

function saveScoreEvent(){
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"score" : score.innerText,"gameNo" : 6}, // 숫자야구 게임넘버 6임
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

