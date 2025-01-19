
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

document.addEventListener("DOMContentLoaded", () => {
    // 현재 URL의 쿼리 파라미터 추출
    const params = new URLSearchParams(window.location.search);

    // dnPetCategory와 productCategory 값 확인
    const dnPetCategory = params.get("dnPetCategory");
    const productCategory = params.get("productCategory");

    // 모든 a 태그를 선택
    const links = document.querySelectorAll(".SubView-tab-t1");

    links.forEach(link => {
        const category = link.dataset.category;

        // 현재 카테고리 값과 URL 파라미터 값을 비교해 active 클래스 추가
        if (category === dnPetCategory || category === productCategory) {
            link.classList.add("active");
        }
    });
});



