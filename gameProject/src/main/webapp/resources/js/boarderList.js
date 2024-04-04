
// 클릭했을 경우 고정시키기
// 필요함수선언
const navCol1 = document.getElementsByClassName("navCol1")[0];
const navCol2 = document.getElementsByClassName("navCol2")[0];
const navCol3 = document.getElementsByClassName("navCol3")[0];
const navCol4 = document.getElementsByClassName("navCol4")[0];
(function(){
    const params = new URL(location.href).searchParams;
    
    const kind = params.get("kind");
    const cp = params.get("cp");
    if(kind > 0){
        if(kind == 1){
            navCol1.setAttribute("id" , "onV");
        }
        if(kind == 2){
            navCol2.setAttribute("id" , "onV");
        }
        if(kind == 3){
            navCol3.setAttribute("id" , "onV");
        }
        if(kind == 4){
            navCol4.setAttribute("id" , "onV");
        }
    }
})();