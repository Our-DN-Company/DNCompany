document.addEventListener("DOMContentLoaded", function () {

    // DOM 요소 참조 변수
    const itemsPerPageSelect = document.getElementById('itemsPerPage');
    const searchForm = document.getElementById('searchForm');
    const selectAllCheckbox = document.getElementById('selectAll');
    const memberListBody = document.getElementById('memberListBody');

    // 페이지당 게시물 수 선택 변경 이벤트 리스너
    itemsPerPageSelect.addEventListener("change", function () {
        searchForm.submit();
    });

    // 폼 제출 시 검색 기능 처리 (AJAX 방식)
    searchForm.addEventListener("submit", function (e) {
        e.preventDefault();  // 기본 폼 제출을 막고, 서버로 데이터를 전송

        // 폼 데이터를 FormData 객체로 생성
        const formData = new FormData(searchForm);

        // 데이터를 비동기적으로 서버에 전송
        fetch("/admin/user/board/list/data", {
            method: "POST",
            body: formData
        })
            .then(response => response.text())
            .then(html => {
                // 서버에서 받은 HTML을 memberListBody에 삽입
                memberListBody.innerHTML = html;
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });

    // 전체 선택/해제 체크박스 기능
    selectAllCheckbox.addEventListener("change", function () {
        const checkboxes = memberListBody.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });

    // 포인트 적용 함수
    window.applyCustomPoints = function (userId) {
        const pointInput = document.getElementById(`point-${userId}`);
        const points = pointInput.value;
        if (points) {
            alert(`포인트 ${points}가 사용자 ${userId}에 적용되었습니다.`);
        } else {
            alert('포인트 값을 입력해주세요.');
        }
    };

    // 활동 정지 함수
    window.applyBan = function (userId, days) {
        alert(`${days}일 동안 사용자 ${userId}의 활동이 정지되었습니다.`);
    };

    // 커스텀 활동 정지 함수
    window.applyCustomBan = function (userId) {
        const banInput = document.getElementById(`ban-${userId}`);
        const days = banInput.value;
        if (days) {
            applyBan(userId, days);
        } else {
            alert('정지 일수를 입력해주세요.');
        }
    };
});
