let currentPage = 1;
let isLoading = false
let hasNext = true;

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

    async function loadPage(page) {
        try {
            isLoading = true;

            const response = await fetch(`/zip/community?page=${page}`);
            if (!response.ok) throw new Error("데이터를 가져오는 데 실패했습니다.");

            const { data, hasNextPage } = await response.json();

            renderList(data);

            currentPage = page;
            hasNext = hasNextPage;
        } catch (error) {
            console.error(error.message);
        } finally {
            isLoading = false;
        }
    }

    async function loadNextPage() {
        if (!hasNext) return;
        await loadPage(currentPage + 1);
    }

    function renderList(data) {
        if (!Array.isArray(data)) return;

        $listContainer.innerHTML = "";

        data.forEach((item) => {
            const $item = document.createElement("div");
            $item.className = "qaList_qaListWrapper";
            $item.innerHTML = `
                <div>
                    <div class="qaList_qaListTitle">
                        <a href="/zip/detail?zipId=${item.zipId}">${item.zipTitle}</a>
                    </div>
                    <div class="qaList_qaListText">${item.zipContent}</div>
                </div>
            `;
            $listContainer.appendChild($item);
        });
    }

    initPagination();

    loadPage(currentPage);


    const writeButton = document.querySelector(".write_Btn_item");
    if (writeButton) {
        writeButton.addEventListener("click", () => {
            window.location.href = "/zip/write";
        });
    }
});



