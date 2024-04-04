const preview = document.getElementsByClassName("preview");
const inputImage = document.getElementsByClassName("inputImage");
const deleteImage = document.getElementsByClassName("delete-image");

const deleteList = document.getElementById("deleteList");
const deleteSet = new Set();
const boardCategory = document.getElementById("boardCategory");
(
    function(){

        const params = new URL(location.href).searchParams;
        const type = params.get("kind");
        // url의 카테고리를 읽어와서 셀렉트 박스에 selected 해줌
        for(let i =0; i<boardCategory.options.length; i++){
            if(type == boardCategory[i].value){
                boardCategory.options[i].selected = true;
            }
        }
    }
)()

for (let i = 0; i < inputImage.length; i++) {
    inputImage[i].addEventListener("change",function(){
        if(this.files[0] != undefined){
            const reader = new FileReader();

            reader.readAsDataURL(this.files[0]);

            reader.onload = function(e){
                preview[i].setAttribute("src",e.target.result);
                if(i==0){
                    deleteSet.delete(bNo0);
                }
                if(i==1){
                    deleteSet.delete(bNo1);
                }
                if(i==2){
                    deleteSet.delete(bNo2);
                }
            }
        }else{
            preview[i].removeAttribute("src");
        }
    })

    deleteImage[i].addEventListener("click",function(){

        preview[i].removeAttribute("src");
        inputImage[i].value="";

        if(i==0){
            deleteSet.add(bNo0);
        }
        if(i==1){
            deleteSet.add(bNo1);
        }
        if(i==2){
            deleteSet.add(bNo2);
        }
    })
}

// form 태그 값 체크
function writeValidate(){
    const boardTitle = document.getElementById("boardTitle");
    const boardContent = document.getElementById("boardContent");
    const category = document.getElementById("boardCategory");

    if(boardTitle.value.trim().length == 0){
        return printAlert(boardTitle,"게시글 제목을 입력해주세요.");
    }

    if(category.value == "0"){

        alert("카테고리를 선택해주세요.")
        return false;
    }

    if(boardContent.value.trim().length == 0){
        return printAlert(boardContent,"게시글 내용을 입력해주세요.");
    }

    deleteList.value = Array.from(deleteSet);
    
    
    return true;
}

function printAlert(el,msg){
    alert(msg);
    el.value="";
    el.focus();
    return false;
}
