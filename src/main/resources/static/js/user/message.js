import * as userApi from './modules/userApi.js';

const $messageSend = document.querySelector('.message__btn');
const $openModal = document.querySelector('.message_modal_box');
const $closeModal = document.querySelector('.message_btn_close');
const $form = document.querySelector('.message_form');
const $messageUserTo = document.querySelector("#loginIdTo");

// 모달 팝업 열기
$messageSend.addEventListener('click', function() {
    $openModal.style.display = 'flex';
});

// 모달 팝업 닫기
$closeModal.addEventListener('click', function() {
    $openModal.style.display = 'none';
});

// 아이디 중복 검사 및 폼 제출 처리
async function handleUsernameValidation(value) {
    if (!value) {
        alert("아이디를 입력하세요!");  // 아이디가 비어있는 경우 알림 표시
        return false;
    }

    try {
        const data = await userApi.checkLoginId(value);
        if (data.exists) {
            return true;  // 중복된 아이디인 경우
        } else {
            return false;  // 중복되지 않은 아이디인 경우
        }
    } catch (error) {
        console.error("중복 검사 오류:", error);
        return false;
    }
}

// 폼 제출 시 아이디 중복 여부 확인 후 쪽지 전송 처리
$form.addEventListener('submit', async function (e) {
    e.preventDefault();  // 기본 폼 제출 방지

    const value = $messageUserTo.value;
    const isUsernameValid = await handleUsernameValidation(value);  // 아이디 중복 검사 결과

    if (isUsernameValid) {
        // 중복된 아이디인 경우 쪽지 전송
        alert("쪽지 전송 완료!");
        this.submit();  // 폼을 실제로 제출
    } else {
        // 중복되지 않은 아이디인 경우
        alert("아이디를 다시 입력하세요");
        $messageUserTo.value = "";
    }
});
