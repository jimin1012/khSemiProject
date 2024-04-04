

document.getElementById('allCheck').addEventListener('click', function () {
    // id가 "allCheck"인 체크박스 요소 가져오기
    var allCheck = document.getElementById('allCheck');

    // 모든 체크박스 요소들 가져오기
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // "allCheck" 체크박스가 변경될 때 이벤트 처리
    allCheck.addEventListener('change', function () {
        // "allCheck" 체크박스의 상태에 따라 모든 체크박스들의 상태 변경
        checkboxes.forEach(function (checkbox) {
            checkbox.checked = allCheck.checked;
        });
    });
});



document.getElementById("nextbtn").addEventListener("click", function(event) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    var allChecked = true;

    checkboxes.forEach(function(checkbox) {
        if (!checkbox.checked) {
            allChecked = false;
        }
    });

   if (!tosCheck.checked) {
        alert('이용약관에 동의해야 합니다.');
        event.preventDefault(); // 다음 페이지로의 이동을 막음
    }    
    

    
});

document.addEventListener("DOMContentLoaded", 
function(){
    // DOM이 로드된 후 실행될 코드

    // 전체 동의 체크박스
    var allCheck = document.getElementById("allCheck");

    // 개별 동의 체크박스들
    var tosCheck = document.getElementById("tosCheck");
    var marketingCheck = document.getElementById("marketingCheck");
    var manager = document.getElementById("manager");

    // 전체 동의 체크박스에 이벤트 리스너 추가
    allCheck.addEventListener("change", function () {
        // 전체 동의 체크박스의 상태에 따라 개별 동의 체크박스들을 변경
        tosCheck.checked = allCheck.checked;
        marketingCheck.checked = allCheck.checked;
        manager.checked = allCheck.checked;
    });

    // 개별 동의 체크박스들에 이벤트 리스너 추가
    tosCheck.addEventListener("change", updateAllCheck);
    marketingCheck.addEventListener("change", updateAllCheck);
    manager.addEventListener("change", updateAllCheck);

    // 개별 동의 체크박스의 변경에 따라 전체 동의 체크박스 업데이트하는 함수
    function updateAllCheck() {
        allCheck.checked = tosCheck.checked && marketingCheck.checked && manager.checked;
    }
});