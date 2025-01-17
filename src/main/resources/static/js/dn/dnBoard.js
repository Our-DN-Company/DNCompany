
// 정렬 버튼 처리
// 라디오 버튼을 받아와야한다.
{
const $radioContainer = document.querySelector('.shopping_productFilterContainer');

    $radioContainer.addEventListener("change", (e) => {
        console.log(e.target)
        if (e.target.classList.contains("radio_myRadio")) {
            const searchParams = new URLSearchParams(location.search);
            searchParams.set("order", e.target.value); // 라디오 버튼 값 추가
            location.search = searchParams.toString(); // 페이지 새로고침과 함께 URL 변경
        }
    });
}


