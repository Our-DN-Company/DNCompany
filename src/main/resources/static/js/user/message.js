
const $messageSend = document.querySelector('.message__btn')
const $openModal = document.querySelector('.message_modal_box')
const $closeModal = document.querySelector('.message_btn_close')

//열기 버튼을 눌렀을 때 모달팝업이 열림
$messageSend.addEventListener('click',function(){
    //'on' class 추가
    $openModal.style.display = 'flex';
});
//닫기 버튼을 눌렀을 때 모달팝업이 닫힘
$closeModal.addEventListener('click',function() {
    //'on' class 제거
    $openModal.style.display = 'none';
});