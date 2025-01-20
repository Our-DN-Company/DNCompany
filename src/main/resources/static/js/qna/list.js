let currentPage = 1; // 현재 페이지
let isLoading = false; // 로딩 상태
let hasNext = true; // 다음 페이지 여부

document.addEventListener("DOMContentLoaded", () => {
    const $listContainer = document.querySelector(".community_loungeList"); // 게시글 리스트 영역
    const $paginationContainer = document.querySelector(".pagination-section"); // 페이지네이션 영역

    function initPagination() {
        if (!$paginationContainer || !$listContainer) return;

        const $nextButton = $paginationContainer.querySelector(".next");
        if ($nextButton) {
            $nextButton.addEventListener("click", async () => {
                if (isLoading || !hasNext) return;
                await loadNextPage();
            });
        }

        const $prevButton = $paginationContainer.querySelector(".prev");
        if ($prevButton) {
            $prevButton.addEventListener("click", async () => {
                if (isLoading || currentPage <= 1) return;
                currentPage--; // 이전 페이지로 이동
                await loadPage(currentPage);
            });
        }
    }




    initPagination();

    loadPage(currentPage);


    const writeButton = document.querySelector(".write_Btn_item");
    if (writeButton) {
        writeButton.addEventListener("click", () => {
            window.location.href = "/qna/write";
        });
    }
});



