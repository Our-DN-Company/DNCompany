import * as userApi from '../modules/userApi.js';
import * as messageListApi from '/modules/messageListApi';

// HTML과 이벤트 처리
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
function handleUsernameValidation(username, callback) {
    if (!username.trim()) {
        alert("아이디를 입력하세요!");  // 아이디가 비어있는 경우 알림 표시
        callback(false);  // callback을 통해 false 반환
        return;
    }

    // 아이디 중복 검사 API 호출
    userApi.checkLoginId(username, function(data) {  // userApi에서 checkLoginId 호출
        if (data && data.exists !== undefined) {  // data.exists가 정상적으로 존재하는지 확인
            if (data.exists) {
                callback(true);  // 중복된 아이디일 경우 true 반환
            } else {
                alert("아이디를 다시 입력하세요.");
                callback(false);  // 중복되지 않은 아이디일 경우 false 반환
            }
        } else {
            alert("API 응답이 잘못되었습니다. 다시 시도해 주세요.");
            callback(false);  // API 응답이 예상과 다를 경우 처리
        }
    });
}

// 폼 제출 시 아이디 중복 여부 확인 후 쪽지 전송 처리
$form.addEventListener('submit', function (e) {
    e.preventDefault();  // 기본 폼 제출 방지

    const value = $messageUserTo.value.trim();  // 입력값에서 공백 제거
    console.log("입력된 아이디:", value);  // 디버깅

    handleUsernameValidation(value, function(isUsernameValid) {  // 아이디 중복 검사 결과
        if (isUsernameValid) {
            alert("쪽지 전송 완료!");
            // 폼을 실제로 제출 (AJAX로 처리하거나 폼 전송을 해도 됩니다)
            // 서버로 데이터를 보내는 방법을 추가하세요.
            $form.submit();  // submit 호출로 폼을 전송 (아이디 중복 확인 후)
        } else {
            $messageUserTo.value = "";  // 입력 필드 초기화
            $messageUserTo.focus();  // 아이디 입력 필드로 포커스 이동
        }
    });
});

const $messagedetail = document.querySelectorAll('.message__info__title__user');
const $openreciveModal = document.querySelector('.message_receive_modal_box');
const $closereciveModal = document.querySelector('.message_receive__btn_close'); // 닫기 버튼 수정

// // 모달 팝업 열기
// $messagedetail.addEventListener('click', function() {
//     $openreciveModal.style.display = 'flex';
// });
//
// 모달 팝업 닫기

document.addEventListener("click", function (e) {
    const $currentElement = e.target;

    if ($currentElement.classList.contains('info__title__user')) {
        $openreciveModal.style.display = 'flex';
    }
});

$closereciveModal.addEventListener('click', function() {
    $openreciveModal.style.display = 'none';
});

let currentPage = 1;      // 현재 페이지 번호를 관리하는 변수
let isLoading = false;    // 현재 페이지를 불러오는 중인지 여부를 저장할 변수
let hasNext = true;       // 다음 페이지 존재여부 저장할 변수


// From

{

    document.addEventListener('DOMContentLoaded', () => {
        const $messageFromList = document.querySelector('.message__info__list'); // 메시지 리스트 컨테이너
        const $messageFromCount = document.querySelector('#paginationFrom'); // 메시지 총 개수 표시 영역
        const $pagination = document.querySelector('.pagination'); // 페이지 번호 버튼들

        // 페이지 초기화
        loadComments();

        // 페이지 번호 버튼 클릭 시 페이지 변경
        $pagination.addEventListener('click', (e) => {
            if (e.target && e.target.classList.contains('page-btn')) {
                currentPage = parseInt(e.target.dataset.page); // 클릭한 버튼의 페이지 번호로 변경
                loadComments();
            }
        });

        function loadComments() {
            // 중복 로드 방지 및 다음 페이지 확인
            if (isLoading || !hasNext) return;

            isLoading = true;

            // 메시지 목록 API 호출
            messageListApi.messageWithFromPage(usersId, currentPage, (data) => {
                console.log(data); // 디버깅용 콘솔 출력
                displayMessageFrom(data); // 데이터 렌더링
                isLoading = false; // 로드 상태 해제
                hasNext = data.pageDTO.hasNext; // 다음 페이지 존재 여부 업데이트
            });
        }

        function displayMessageFrom(obj) {
            $messageFromCount.textContent = obj.total; // 총 메시지 개수 업데이트

            let html = '';

            // 데이터 렌더링
            obj.pageDTO.list.forEach((messageFrom) => {
                html += `
            <div class="message__info__title__user">
                <p class="info__title__user">${escapeHtml(messageFrom.loginId)}님</p>
                <p class="info__title__user">${escapeHtml(messageFrom.msContent)}</p>
                <button class="title__delete__btn" data-comment-id="${messageFrom.userFrom}">삭제</button>
            </div>
            `;
            });

            // DOM 업데이트
            $messageFromList.innerHTML = html; // 페이지를 새로고침할 때마다 리스트 갱신
        }

        // HTML 이스케이프 처리 함수
        function escapeHtml(unsafe) {
            return unsafe
                .replace(/&/g, '&amp;')
                .replace(/</g, '&lt;')
                .replace(/>/g, '&gt;')
                .replace(/"/g, '&quot;')
                .replace(/'/g, '&#039;');
        }
    });


}









