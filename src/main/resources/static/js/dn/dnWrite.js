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

const elProductPrice = document.querySelector("#productPrice");

elProductPrice.addEventListener("input", function (e) {
const currentValue = e.target.value;

// 숫자 외 문자 제거
e.target.value = currentValue.replace(/[^0-9]/g, "");

});