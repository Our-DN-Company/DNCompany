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

// 비동기 처리 시작 To

    function handlemessageListTOData(data) {
        console.log(data);
        displaymessageListTo(data.list);
        messageToPageGroup(data);
    }

    {


        messageListApi.getMessageListTo(1, handlemessageListTOData)

        const $pageGroupContainer = document.querySelector('#paginationTo');

        $pageGroupContainer.addEventListener('click', function (e) {
            if (e.target.tagName === 'A') {
                const page = e.target.dataset.page;

                messageListApi.getMessageListTo(page, handlemessageListTOData)
            }

        });

    }

    function displaymessageListTo(messageList) {
        console.log('displayZipList', messageList);

        let html = '';

        messageList.forEach(message => {
            html += `
            <div id="messageFromList" class="message__info__title__user">
                 <p class="info__title__user">${message.loginId}</p>
                 <p class="info__title__user">${message.msContent}</p>
                 <button class="title__delete__btn" data-message-id="${message.messageId}">삭제</button>
            </div>
        `;
        });

        document.querySelector('#messageToListContainer').innerHTML = html;
    }

    function messageToPageGroup(pageDTO) {

        let html = ``;

        if (pageDTO.startPage > 1) {
            html += `
        <a class="page-btn prev" data-page="${pageDTO.startPage - 1}">&lt;</a>
        `;
        }


        for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
            html += `
        <a class="page-btn" ${pageDTO.page == i ? 'active' : ''}" data-page="${i}">${i}</a>
        `;
        }

        if (pageDTO.endPage < pageDTO.totalPages) {
            html += `
           <a class="page-btn next" data-page="${pageDTO.endPage + 1}">&gt;</a>
        `;
        }


        document.querySelector('#paginationTo').innerHTML = html;
    }

// 비동기 처리 시작 From
    function handlemessageListFromData(data) {
        console.log(data);
        displaymessageListFrom(data.list);
        messageFromPageGroup(data);
    }

    {


        messageListApi.getMessageListFrom(1, handlemessageListFromData)

        const $pageGroupContainer = document.querySelector('#paginationFrom');

        $pageGroupContainer.addEventListener('click', function (e) {
            if (e.target.tagName === 'A') {
                const page = e.target.dataset.page;

                messageListApi.getMessageListFrom(page, handlemessageListFromData)
            }

        });

    }

    function displaymessageListFrom(messageList) {
        console.log('displayZipList', messageList);

        let html = '';

        messageList.forEach(message => {
            html += `
            <div id="messageFromList" class="message__info__title__user">
                 <p class="info__title__user">${message.loginId}</p>
                 <p class="info__title__user">${message.msContent}</p>
                 <button class="title__delete__btn" data-message-id="${message.messageId}">삭제</button>
            </div>
        `;
        });

        document.querySelector('#messageFromListContainer').innerHTML = html;
    }

    function messageFromPageGroup(pageDTO) {

        let html = ``;

        if (pageDTO.startPage > 1) {
            html += `
        <a class="page-btn prev" data-page="${pageDTO.startPage - 1}">&lt;</a>
        `;
        }


        for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
            html += `
        <a class="page-btn" ${pageDTO.page == i ? 'active' : ''}" data-page="${i}">${i}</a>
        `;
        }

        if (pageDTO.endPage < pageDTO.totalPages) {
            html += `
           <a class="page-btn next" data-page="${pageDTO.endPage + 1}">&gt;</a>
        `;
        }


        document.querySelector('#paginationFrom').innerHTML = html;
    }

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

