document.addEventListener("DOMContentLoaded", () => {
    const tableRows = document.querySelectorAll(".mypage_main_list_top tr"); // 게시판 목록 행 선택
    const prevButton = document.createElement("button"); // 이전 버튼 생성
    const nextButton = document.createElement("button"); // 다음 버튼 생성
    const rowsPerPage = 5; // 한 페이지에 표시할 행 수
    let currentIndex = 0; // 현재 페이지의 시작 인덱스

    // 버튼에 텍스트 설정
    prevButton.textContent = "이전";
    nextButton.textContent = "다음";

    // 버튼에 클래스 추가
    prevButton.classList.add("mypage-nav-prev");
    nextButton.classList.add("mypage-nav-next");

    // 버튼 삽입 (게시판 테이블 아래에 삽입)
    const tableContainer = document.querySelector(".mypage_main_help_me"); // '도와주세요' 테이블 기준
    tableContainer.appendChild(prevButton);
    tableContainer.appendChild(nextButton);

    // 행 가시성 업데이트 함수
    const updateTableRows = () => {
        tableRows.forEach((row, index) => {
            if (index === 0 || (index > currentIndex && index <= currentIndex + rowsPerPage)) {
                row.style.display = ""; // 표시
            } else {
                row.style.display = "none"; // 숨김
            }
        });

        // 버튼 상태 업데이트
        prevButton.disabled = currentIndex === 0; // 첫 번째 페이지에서는 이전 버튼 비활성화
        nextButton.disabled = currentIndex + rowsPerPage >= tableRows.length - 1; // 마지막 페이지에서는 다음 버튼 비활성화
    };

    // 이전 버튼 클릭 이벤트
    prevButton.addEventListener("click", () => {
        if (currentIndex > 0) {
            currentIndex -= rowsPerPage;
            updateTableRows();
        }
    });

    // 다음 버튼 클릭 이벤트
    nextButton.addEventListener("click", () => {
        if (currentIndex + rowsPerPage < tableRows.length - 1) {
            currentIndex += rowsPerPage;
            updateTableRows();
        }
    });

    // 초기화
    updateTableRows();
});
