import * as userApi from '../modules/userApi.js';
import * as messageListApi from '../modules/messageListApi.js';
import {deleteMessage} from "../modules/messageListApi.js";

// HTML과 이벤트 처리
const $messageSend = document.querySelector('.message__btn');
const $openModal = document.querySelector('.message_modal_box');
const $closeModal = document.querySelector('.message_btn_close');
const $form = document.querySelector('.message_form');
const $messageUserTo = document.querySelector("#loginIdTo");

// 모달 팝업 열기
$messageSend.addEventListener('click', function () {
    $openModal.style.display = 'flex';
});

// 모달 팝업 닫기
$closeModal.addEventListener('click', function () {
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
    userApi.checkLoginId(username, function (data) {  // userApi에서 checkLoginId 호출
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

    handleUsernameValidation(value, function (isUsernameValid) {  // 아이디 중복 검사 결과
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

//
// const $openreciveModal = document.querySelector('.message_receive_modal_box');
// const $closereciveModal = document.querySelector('.message_receive__btn_close'); // 닫기 버튼 수정
//
//
// document.addEventListener("click", function (e) {
//     const $currentElement = e.target;
//
//     if ($currentElement.classList.contains('info__title__user')) {
//         $openreciveModal.style.display = 'flex';
//     }
// });
//
// $closereciveModal.addEventListener('click', function() {
//     $openreciveModal.style.display = 'none';
// });
//

{

    const $deleteBtn = document.querySelector(".title__delete__btn")
    $deleteBtn?.addEventListener('click', function () {
        if (confirm('정말 삭제하시겠습니까?')) {
            // TODO: 서버에 삭제 요청
            console.dir(this)
            const messageId = this.dataset.messageId;
            location.href = `/user/message?messageId=${messageId}`;
        }
    });

// 받은/보낸 쪽지 모달 DOM 요소 선택
    const $receivedModal = document.querySelector('.received-modal'); // 받은 쪽지 모달
    const $sentModal = document.querySelector('.sent-modal'); // 보낸 쪽지 모달

// 닫기 버튼 DOM 요소 선택
    const $closeReceivedModal = $receivedModal.querySelector('.message_receive__btn_close'); // 받은 쪽지 모달 닫기 버튼
    const $closeSentModal = $sentModal.querySelector('.message_receive__btn_close'); // 보낸 쪽지 모달 닫기 버튼

// ====================== 받은 쪽지 비동기 처리 ======================

// 받은 쪽지 데이터를 받아와 화면에 표시하고 페이지 그룹 생성
    function handlemessageListTOData(data) {
        displaymessageListTo(data.list); // 받은 쪽지 목록 표시
        messageToPageGroup(data); // 페이지네이션 처리
    }

// 초기 로드 시 1페이지 데이터를 요청
    messageListApi.getMessageListTo(1, handlemessageListTOData);

// 페이지네이션 클릭 이벤트 리스너 추가
    const $toPageGroupContainer = document.querySelector('#paginationTo');
    $toPageGroupContainer.addEventListener('click', function (e) {
        if (e.target.tagName === 'A') {
            const page = e.target.dataset.page; // 클릭한 페이지 번호 가져오기
            messageListApi.getMessageListTo(page, handlemessageListTOData); // 해당 페이지 데이터 요청
        }
    });

// 받은 쪽지 목록을 동적으로 생성하여 화면에 추가
    function displaymessageListTo(messageList) {
        let html = ''; // HTML 문자열 초기화
        messageList.forEach((message) => {
            // 각 쪽지 데이터를 기반으로 HTML 구성
            html += `
            <div class="message__info__title__user" data-login-id="${message.loginId}" data-content="${message.msContent}">
                <p class="info__title__user">${message.loginId}</p>
                <p class="info__title__user">${message.msContent}</p>
                <button class="title__delete__btn" data-message-id="${message.messageId}">삭제</button>
            </div>
        `;
        });
        document.querySelector('#messageToListContainer').innerHTML = html; // HTML 삽입
    }

// 받은 쪽지 페이지네이션 HTML 동적으로 생성
    function messageToPageGroup(pageDTO) {
        let html = ``; // HTML 문자열 초기화

        // 이전 페이지 버튼 생성
        if (pageDTO.startPage > 1) {
            html += `<a class="page-btn prev" data-page="${pageDTO.startPage - 1}">&lt;</a>`;
        }

        // 현재 페이지 범위의 버튼 생성
        for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
            html += `<a class="page-btn ${pageDTO.page == i ? 'active' : ''}" data-page="${i}">${i}</a>`;
        }

        // 다음 페이지 버튼 생성
        if (pageDTO.endPage < pageDTO.totalPages) {
            html += `<a class="page-btn next" data-page="${pageDTO.endPage + 1}">&gt;</a>`;
        }

        document.querySelector('#paginationTo').innerHTML = html; // HTML 삽입
    }

// ====================== 보낸 쪽지 비동기 처리 ======================

// 보낸 쪽지 데이터를 받아와 화면에 표시하고 페이지 그룹 생성
    function handlemessageListFromData(data) {
        displaymessageListFrom(data.list); // 보낸 쪽지 목록 표시
        messageFromPageGroup(data); // 페이지네이션 처리
    }

// 초기 로드 시 1페이지 데이터를 요청
    messageListApi.getMessageListFrom(1, handlemessageListFromData);

// 페이지네이션 클릭 이벤트 리스너 추가
    const $fromPageGroupContainer = document.querySelector('#paginationFrom');
    $fromPageGroupContainer.addEventListener('click', function (e) {
        if (e.target.tagName === 'A') {
            const page = e.target.dataset.page; // 클릭한 페이지 번호 가져오기
            messageListApi.getMessageListFrom(page, handlemessageListFromData); // 해당 페이지 데이터 요청
        }
    });

// 보낸 쪽지 목록을 동적으로 생성하여 화면에 추가
    function displaymessageListFrom(messageList) {
        let html = ''; // HTML 문자열 초기화
        messageList.forEach((message) => {
            // 각 쪽지 데이터를 기반으로 HTML 구성
            html += `
            <div class="message__info__title__user" data-login-id="${message.loginId}" data-content="${message.msContent}">
                <p class="info__title__user">${message.loginId}</p>
                <p class="info__title__user">${message.msContent}</p>
                <button class="title__delete__btn" data-message-id="${message.messageId}">삭제</button>
            </div>
        `;
        });
        document.querySelector('#messageFromListContainer').innerHTML = html; // HTML 삽입
    }

// 보낸 쪽지 페이지네이션 HTML 동적으로 생성
    function messageFromPageGroup(pageDTO) {
        let html = ``; // HTML 문자열 초기화

        // 이전 페이지 버튼 생성
        if (pageDTO.startPage > 1) {
            html += `<a class="page-btn prev" data-page="${pageDTO.startPage - 1}">&lt;</a>`;
        }

        // 현재 페이지 범위의 버튼 생성
        for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
            html += `<a class="page-btn ${pageDTO.page == i ? 'active' : ''}" data-page="${i}">${i}</a>`;
        }

        // 다음 페이지 버튼 생성
        if (pageDTO.endPage < pageDTO.totalPages) {
            html += `<a class="page-btn next" data-page="${pageDTO.endPage + 1}">&gt;</a>`;
        }

        document.querySelector('#paginationFrom').innerHTML = html; // HTML 삽입
    }

// ====================== 모달 열기 및 닫기 처리 ======================

// 클릭 이벤트로 받은/보낸 쪽지 모달 열기
    document.addEventListener('click', function (e) {
        const $target = e.target;

        // 받은 쪽지 모달 열기
        if ($target.closest('#messageToListContainer')) {
            const $messageItem = $target.closest('.message__info__title__user');
            if ($messageItem) {
                const sender = $messageItem.dataset.loginId; // 보낸 사람
                const content = $messageItem.dataset.content; // 쪽지 내용
                $receivedModal.querySelector('.modal-sender').textContent = sender;
                $receivedModal.querySelector('.modal-content').textContent = content;
                $receivedModal.style.display = 'flex'; // 모달 표시
            }
        }

        // 보낸 쪽지 모달 열기
        if ($target.closest('#messageFromListContainer')) {
            const $messageItem = $target.closest('.message__info__title__user');
            if ($messageItem) {
                const receiver = $messageItem.dataset.loginId; // 받은 사람
                const content = $messageItem.dataset.content; // 쪽지 내용
                $sentModal.querySelector('.modal-receiver').textContent = receiver;
                $sentModal.querySelector('.modal-content').textContent = content;
                $sentModal.style.display = 'flex'; // 모달 표시
            }
        }
    });

// 받은 쪽지 모달 닫기
    $closeReceivedModal.addEventListener('click', () => {
        $receivedModal.style.display = 'none';
    });

// 보낸 쪽지 모달 닫기
    $closeSentModal.addEventListener('click', () => {
        $sentModal.style.display = 'none';
    });

    document.addEventListener('click', function (e) {
        if (e.target.classList.contains('title__delete__btn')) {
            if (confirm('메세지를 삭제하시겠습니까?')) {
                const messageId = e.target.dataset.messageId;
                messageListApi.deleteMessage(messageId, function () {
                    messageListApi.getMessageListFrom(1, handlemessageListFromData);
                    messageListApi.getMessageListTo(1, handlemessageListTOData);
                })

            }

        }
    });

}

