
/* 선택 버튼 */
const btn = document.getElementsByTagName("button");

/* 달팽이 */
const snail01 = document.getElementById("snail01");
const snail02 = document.getElementById("snail02");
const snail03 = document.getElementById("snail03");

/* 점수 길 결과 */
const score = document.getElementById("score");
const load = document.getElementsByClassName("load");
const result = document.getElementById("result");

/* 팝업 */
const popup = document.getElementsByClassName("popup");
const save = document.getElementById("save");
const restart = document.getElementById("restart");

let random =0;
let Windistance = 0;
let loserdistance = 0;
let loserdistance01 = 0;
let count = 0;



for (let i = 0; i < btn.length; i++) {
    btn[i].addEventListener("click",function(){
        const str = this.innerText;
        result.innerText = "결과 대기중..."
        this.innerText = "이걸 골라?"
        snail(str);

        for(let j = 0; j < btn.length; j++){
            btn[j].disabled = true;// 버튼 비활성화
        }
        
    })
}

// 게임 재시작
function gameReStart(){
    popup[0].style.display = "none";
    result.innerText = "";
    score.innerText = 0;
}

function snail(str){
    random= Math.floor(Math.random()*3+1);
    

    interval = setInterval(function(){
        Windistance += Math.floor(Math.random()*10+1);
        loserdistance += Math.floor(Math.random()*7+1);
        loserdistance01 += Math.floor(Math.random()*4+2)
        if(Windistance < (load[0].clientWidth-50)){
            if(random == 1){
                snail01.style.transform ="translateX("+Windistance+"px)";
                snail02.style.transform ="translateX("+(loserdistance01+Math.floor(Math.random()*3+1))+"px)";
                snail03.style.transform ="translateX("+(loserdistance+Math.floor(Math.random()*5+1))+"px)";
            }else if(random == 2){
                snail01.style.transform ="translateX("+(loserdistance+Math.floor(Math.random()*3+1))+"px)";
                snail02.style.transform ="translateX("+Windistance+"px)";
                snail03.style.transform ="translateX("+(loserdistance01+Math.floor(Math.random()*2+1)) +"px)";
            }else{
                snail01.style.transform ="translateX("+(loserdistance01+Math.floor(Math.random()*6+1)) +"px)";
                snail02.style.transform ="translateX("+(loserdistance+Math.floor(Math.random()*5+1)) +"px)";
                snail03.style.transform ="translateX("+Windistance+"px)";
            }
        }else{
            Windistance = 0;
            loserdistance = 0;
            loserdistance01 = 0;

            if(str.substring(0,1) == random){
                result.innerText = "****이열~정답!!!!!****";
                score.innerText = ++count;
            }else{
                result.innerText = "****풉 ㅋ 이걸 못 맞추네****";
                count = 0;
                
                popup[0].style.display = "block";
            }

            clearInterval(interval);

            snail01.style.transform ="translateX(0px)";
            snail02.style.transform ="translateX(0px)";
            snail03.style.transform ="translateX(0px)";
            for(let j = 0; j < btn.length; j++){
                btn[j].disabled = false;// 버튼 활성화
                btn[j].innerText = j+1 +"번 달팽이";
            }
        }
        
    },100);
}

save.addEventListener("click",function(){
    let flag = false;
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"memberNo" : memberNo,"gameNo" : 3}, // 달팽이게임 GAME DB에 3번으로 저장되어 있음
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

function saveScoreEvent(){
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"score" : score.innerText,"gameNo" : 3}, // GAME DB에 달팽이 3번으로 저장되어있음
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