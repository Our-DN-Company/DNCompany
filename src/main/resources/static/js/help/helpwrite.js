function confirmSubmit(event) {
    event.preventDefault(); // 폼 제출 기본 동작 중지

    console.log('click!')

    if(confirm('등록하시겠습니까?')) {
        console.log("등록~~~")
        document.getElementById('petForm').submit(); // 사용자가 '확인'을 클릭하면 폼 제출
    }
}

// document.getElementById('end-date').addEventListener('change', function() {
//     const startDate = document.getElementById('start-date').value;
//     const endDate = this.value;
//
//     if(startDate && endDate) {
//         if(new Date(endDate) < new Date(startDate)) {
//             alert('종료일은 시작일보다 빠를 수 없습니다.');
//             this.value = '';
//         }
//     }
// });

const elZonecode = document.querySelector("#zonecode");
const elRoadAddress = document.querySelector("#roadAddress");
const elRoadAddressDetail = document.querySelector("#roadAddressDetail");
const elResults = document.querySelectorAll(".el_result");
// 주소검색창 열기 함수
const onClickSearch = () => {
    console.log(1);
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            console.log(data);
            elZonecode.setAttribute("value", data.zonecode);
            elRoadAddress.setAttribute("value", data.address);
        },
    }).open();
};
const register = () => {
    console.log(`우편번호: ${elZonecode.getAttribute("value")}`);
    console.log(`주소: ${elRoadAddress.getAttribute("value")}`);
    console.log(`상세주소: ${elRoadAddressDetail.getAttribute("value")}`);
    elResults[0].innerHTML = elZonecode.getAttribute("value");
    elResults[1].innerHTML = elRoadAddress.getAttribute("value");
    elResults[2].innerHTML = elRoadAddressDetail.getAttribute("value");
};
// 이벤트 추가
document.querySelector("#search-btn").addEventListener("click", () => {
    onClickSearch();
});
// document.querySelector("#register-btn").addEventListener("click", () => {
//     register();
// });
elRoadAddressDetail.addEventListener("change", (e) => {
    elRoadAddressDetail.setAttribute("value", e.target.value);
});