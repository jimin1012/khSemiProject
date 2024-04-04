(function(){
    const list = document.getElementsByClassName("boarderlistArea");
    
    if(k != 1){
        for(let i = 0; i < list.length; i++){
            list[i].addEventListener("click", function(){
    
                    if(document.getElementsByClassName("request")[i].style.display == "flex"){
                        document.getElementsByClassName("request")[i].style.display = "none";
                    }else{
                        document.getElementsByClassName("request")[i].style.display = "flex";
                    }
                
            });
        }
    }

})();
