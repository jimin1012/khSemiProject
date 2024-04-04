/** 가위 바위 보 게임 JS
 * 
 */

let flag = true;
 
player = document.getElementById("player"); // 플레이어 입력 값
computer = document.getElementById("computer"); // 컴퓨터 값

// 게임 승리 또는 비겼을 시 나오는 팝업
popup = document.getElementsByClassName("popup-2")[0];
// 결과(승/무) 저장용
result = document.getElementById("result");
// 게임 패배시 나오는 팝업
lose = document.getElementsByClassName("popup")[0];
// 승리시 스코어 증가를 위함
score = document.getElementById("score");

// 게임 패배시 다시 시작하는 함수
function restart(){
    score.innerHTML = "0";
    lose.style.display = "none";
    startGame();
}

// 게임 시작하는 함수(직접적인 기능)
function startGame(){
    flag = true;
    popup.style.display = "none";
    interval = setInterval(function(){
        let random = Math.floor((Math.random() * 3) + 1);
        computer.value = random;

        if(random == 1) computer.src = "../resources/images/game/scissors.png";
        else if(random == 2) computer.src = "../resources/images/game/rock.png";
        else computer.src = "../resources/images/game/page.png";
    }, 100)

}

// 처음 VS를 눌렀을 시 실행되는 함수
function start(){
    document.getElementById("start").innerHTML = "VS";

    startGame();

    document.getElementById("start").removeAttribute("onclick");
}

// 플레이어가 가위, 바위, 보를 클릭했을 시 컴퓨터와 플레이어의 값을 비교해주는 함수
function test(pVal, cVal){

    clearInterval(interval);
    if(pVal == cVal){ // 비겼을 때
        popup.style.display = "block";
        result.innerHTML = "비겼습니다.";
        flag = false;
        return;
        // startGame();
    }else if( (pVal == 1 && cVal == 3) || (pVal == 2 && cVal == 1) || (pVal == 3 && cVal == 2)){
        // 이겼을 때
        if(flag){
            score.innerHTML = Number(score.innerHTML) + 1;
            flag = false;
        }

        popup.style.display = "block";
        result.innerHTML = "이겼습니다."
    }else{ // 졌을 때
        lose.style.display = "block";
        flag = false;
    }
}

// 가위 눌렀을 시
document.getElementById("scissors").addEventListener("click", function(){
    player.src = "../resources/images/game/scissors.png";
    player.value = 1;
    
    test(player.value, computer.value);
});


// 바위 눌렀을 시
document.getElementById("rock").addEventListener("click", function(){
    player.src = "../resources/images/game/rock.png";
    player.value = 2;

    test(player.value, computer.value);
});

// 보 눌렀을 시
document.getElementById("pager").addEventListener("click", function(){
    player.src = "../resources/images/game/page.png";
    player.value = 3;

    test(player.value, computer.value);
});

const saveBtn = document.getElementById("save");

saveBtn.addEventListener("click",function(){
  

    let flag = false;
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"memberNo" : memberNo,"gameNo" : 4}, // 가위바위보 게임넘버 4
        type : "POST",
        dataType : "JSON",
        success: (res)=>{
  
           if(res == "-1"){
                saveScoreEvent();
           }else{
                for (const i of res) {
                
                    if(i == score.innerHTML){
                        flag = true;
                    }
                }
                if(flag == true){
                    if(confirm("이미 저장 되어있는 점수 입니다. \n 게임을 다시 시작하시겠습니까?")){
                        restart();
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
  
  function saveScoreEvent(){
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"score" : score.innerHTML,"gameNo" : 4}, // GAME DB에 가위바위보 4번으로 저장되어있음
        type : "GET",
        success: (res)=>{
            if(res>0){
                alert("점수가 정상적으로 저장되었습니다!");
                restart();
            }else{
                alert("점수가 정상적으로 저장되지 않았습니다!");
            }
            
        },
        error : function(){
            console.log("에러발생");
        }
    });
  }