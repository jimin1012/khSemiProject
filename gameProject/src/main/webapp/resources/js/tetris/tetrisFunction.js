'use strict';
import Blocks from '../tetris/blocks.js';

export default class Tetris {
  constructor() {
    this.N = 20;
    this.M = 10;
    this.score = 0;
    this.level = 1;
    this.duration = 1000;
    this.time = 1;
    this.timeInterval = undefined;

    this.stage = document.querySelector('.stage');
    this.scoreScreen = document.querySelector('.score');
    this.levelScreen = document.querySelector('.level');

    this.blockInfo = undefined;
    this.movingBlock = undefined;
    this.downInterval = undefined;
    this.nextBlocks = [];
    // 키보드 좌우 위아래 이벤트 (스페이스바)
    document.addEventListener('keydown', (e) => {
      switch (e.keyCode) {
        case 39:
          this.moveBlock('m', 1);
          break;
        case 37:
          this.moveBlock('m', -1);
          break;
        case 40:
          this.moveBlock('n', 1);
          break;
        case 38:
          this.changeDirection();
          break;
        case 32:
          this.dropBlock();
          break;
        default:
          break;
      }
    });
    const restart = document.querySelector('.restart');
    restart.addEventListener('click', () => {
      this.reStart();
    });

   this.saveScore();
   
  }

  
  init() {
    this.score = 0;
    this.scoreScreen.innerText = this.score;
    this.level = 1;
    this.duration = 1000;
    this.levelScreen.innerHTML = this.level;
    this.timeInterval = setInterval(() => {
      this.time += 1;
      if (this.time % 5 == 0) {
        this.setSpeed();
      }
    }, 1000);

   
    this.nextBlocks = [];
    for (let i = 0; i < 4; i++) {
      this.makeNextBlock();
    }
    this.makeGround();
    this.makeNewBlock();
  }

  // 스피드 증가 메소드 최대 속도는 45
  setSpeed() {
    if (this.duration <= 120) {
      return;
    } else {
      this.duration -= 20;
      this.level += 1;
      if (this.level === 45) {
        this.level = '45(MAX)';
      }
      this.levelScreen.innerHTML = this.level;
    }
  }
  // 테트리스의 맵 그리는 메소드
  makeGround() {
    this.ground = [];
    for (let i = 0; i < this.N; i++) {
      this.ground.push('<tr>');
      for (let j = 0; j < this.M; j++) {
        this.ground.push('<td></td>');
      }
      this.ground.push('</tr>');
    }
    this.stage.innerHTML = this.ground.join('');
  }
 // 다음 블럭 나오게 하는 메소드
  makeNextBlock() {
    const blockArray = Object.entries(Blocks);
    const randomIndex = Math.floor(Math.random() * blockArray.length);
    this.nextBlocks.push(blockArray[randomIndex][0]);
  }
  
  // 다음 블럭 이미지 바꾸기
  renderNextBlock() {
    const next = document.querySelector('.next');
    let temp = [];
    for (let i = 0; i < 4; i++) {
      temp.push(
        `<img class='tetris' src="../resources/images/game/tetris/${this.nextBlocks[i]}.png" alt=${this.nextBlocks[i]}"/>`
      );
    }
    next.innerHTML = temp.join('');
  }

  makeNewBlock() {
    const next = this.nextBlocks.shift();
    clearInterval(this.downInterval);

    this.downInterval = setInterval(() => {
      this.moveBlock('n', 1);
    }, this.duration);
    this.blockInfo = {
      type: next,
      direction: 0,
      n: 0,
      m: 3,
    };
    this.movingBlock = { ...this.blockInfo };
    this.renderBlock();
    this.checkNextBlock('start');

    this.makeNextBlock();
    this.renderNextBlock();
  }

  renderBlock() {
    const { type, direction, n, m } = this.movingBlock;
    const temp = document.querySelectorAll('.moving');
    temp.forEach((x) => {
      x.classList.remove(type, 'moving');
    });

    Blocks[type][direction].some((block) => {
      const x = block[0] + n;
      const y = block[1] + m;
      const target = this.stage.childNodes[x]
        ? this.stage.childNodes[x].childNodes[y]
        : null;
      target.classList.add(type, 'moving');
    });

    this.blockInfo.n = n;
    this.blockInfo.m = m;
    this.blockInfo.direction = direction;
  }

  moveBlock(where, amount) {
    this.movingBlock[where] += amount;
    this.checkNextBlock(where);
  }

  checkNextBlock(where = '') {
    const { type, direction, n, m } = this.movingBlock;
    let isFinished = false;
    Blocks[type][direction].some((block) => {
      const x = block[0] + n;
      const y = block[1] + m;
      if (where === 0 || where === 1 || where === 2 || where === 3) {
        this.moveAndTurn();
      } else if (y < 0 || y >= this.M) {
        this.movingBlock = { ...this.blockInfo };
        this.renderBlock();
        return true;
      } else if (x >= this.N) {
        this.movingBlock = { ...this.blockInfo };
        isFinished = true;
        this.finishBlock();
        return true;
      } else {
        const target = this.stage.childNodes[x]
          ? this.stage.childNodes[x].childNodes[y]
          : null;
        if (where === 'm') {
          if (target && target.classList.contains('finish')) {
            this.movingBlock = { ...this.blockInfo };
          }
        } else {
          if (target && target.classList.contains('finish')) {
            isFinished = true;
            this.movingBlock = { ...this.blockInfo };
            if (where === 'start') {
              setTimeout(() => {
                this.finishGame();
              }, 0);
              return true;
            } else {
              this.finishBlock();
              return true;
            }
          }
        }
      }
    });

    if ((where === 'n' || where === 'm') && !isFinished) {
      this.renderBlock();
    }
  }

  moveAndTurn() {
    if (this.movingBlock.m < 0) {
      this.movingBlock.m = 0;
    } else if (this.movingBlock.m + 3 >= this.M) {
      if (this.movingBlock.type === 'I') {
        this.movingBlock.m = 6;
      } else {
        this.movingBlock.m = 7;
      }
    }
  
    this.checkNextBlock('m');
  }

  finishBlock() {
    clearInterval(this.downInterval);
    const temp = document.querySelectorAll('.moving');
    temp.forEach((block) => {
      block.classList.remove('moving');
      block.classList.add('finish');
    });
   
    this.breakBlock();
  }

  breakBlock() {
    let s = 0;
    const tr = this.stage.childNodes;
    tr.forEach((line) => {
      let isBreak = true;
      line.childNodes.forEach((td) => {
        if (!td.classList.contains('finish')) {
          isBreak = false;
        }
      });
      if (isBreak) {
       
        line.remove();
        const tr = document.createElement('tr');
        let ground = [];
        for (let j = 0; j < this.M; j++) {
          ground.push('<td></td>');
        }
        tr.innerHTML = ground.join('');
        this.stage.prepend(tr);
        s++;
      }
    });
    if (s == 1) {
    
    } else if (s == 2) {
     
      s *= 2;
    } else if (s == 3) {
      
      s *= 3;
    } else if (s == 4) {
      
      s *= 4;
    }
    this.score += s;
    this.scoreScreen.innerText = this.score;
    this.makeNewBlock();
  }

  changeDirection() {
    const direction = this.movingBlock.direction;
    direction === 3
      ? (this.movingBlock.direction = 0)
      : (this.movingBlock.direction += 1);
    this.checkNextBlock(direction);
  }

  dropBlock() {
    clearInterval(this.downInterval);
    this.downInterval = setInterval(() => {
      this.moveBlock('n', 1);
    }, 8);
  }

  finishGame() {
    const popup = document.querySelector('.popup');
    popup.style.display = 'flex';
    clearInterval(this.downInterval);
    clearInterval(this.timeInterval);
   
  }

  reStart() {
    const popup = document.querySelector('.popup');
    popup.style.display = 'none';
    this.init();
  }

  saveScoreEvent(score){
    
    $.ajax({
        url : contextPath+"/saveScore",
        data : {"score" : score.innerText,"gameNo" : 1},
        type : "GET",
        success: (res)=>{
            if(res>0){
                alert("점수가 정상적으로 저장되었습니다!");
                this.reStart();
            }else{
                alert("점수가 정상적으로 저장되지 않았습니다!");
            }
            
        },
        error : function(){
            console.log("에러발생");
        }
    });
  }
  saveScore(){
    const saveScoreBtn = document.getElementById("saveScore-btn");
    const score = document.querySelector('.score');
    saveScoreBtn.addEventListener("click",()=>{
        let flag = false;
        $.ajax({
            url : contextPath+"/saveScore",
            data : {"memberNo" : memberNo,"gameNo" : 1},
            type : "POST",
            dataType : "JSON",
            success: (res)=>{
    
              if(res == "-1"){
                    this.saveScoreEvent(score);
              }else{
                    for (const i of res) {
                        if(i == score.innerText){
                            flag = true;
                        }
                    }
                    if(flag == true){
                        if(confirm("이미 저장 되어있는 점수 입니다. \n 게임을 다시 시작하시겠습니까?")){
                          this.reStart();
                        }else{
                            location.href = contextPath;
                        }
                    }else{
                      this.saveScoreEvent(score);
                    }
              }
          
            },
            error : function(){
                console.log("에러발생");
            }
        });

      
     
    });
  }
}
new Tetris().init();
