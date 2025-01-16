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

{   // 정렬 버튼 처리
    // 라디오 버튼을 받아와야한다.
    const $radioContainer = document.querySelector('.shopping_productFilterContainer');

    // 주소창에서 값을 받아와 재정의 하는 코드이다.
    $radioContainer.addEventListener("change", (e) => {
        if (e.target.classList.contains("radio_myRadio")) {

            const searchParams = new URLSearchParams(location.search);
            searchParams.set("order", e.target.value);

            location.search = searchParams.toString();
        }
    });
}
