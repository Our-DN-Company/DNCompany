$('.prouduct_prouductInput').summernote({
    placeholder: '5자 이상의 글 내용을 입력해주세요',
    tabsize: 2,
    height:500,
    toolbar: [
    ['style', ['style']],
    ['font', ['bold', 'underline', 'clear']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['table', ['table']],
    ['insert', ['link', 'picture']],
    ['view', ['fullscreen', 'codeview', 'help']]
    ]
});

// 정렬 버튼 처리
// 라디오 버튼을 받아와야한다.
document.addEventListener("DOMContentLoaded", () => {
    const $radioContainer = document.querySelector('.shopping_productFilterContainer');

    if ($radioContainer) {
        $radioContainer.addEventListener("change", (e) => {
            if (e.target.classList.contains("radio_myRadio")) {
                const searchParams = new URLSearchParams(location.search);
                searchParams.set("order", e.target.value); // 라디오 버튼 값 추가
                location.search = searchParams.toString(); // 페이지 새로고침과 함께 URL 변경
            }
        });
    }
});
