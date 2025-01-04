document.addEventListener("DOMContentLoaded", function () {
    // URL 파라미터 처리
    const urlParams = new URLSearchParams(window.location.search);
    const category = urlParams.get('category');
    if (category) {
        const boardSelect = document.getElementById("boardSelect");
        if (boardSelect) {
            boardSelect.value = category;
        }
    }

    // userImage 클릭 이벤트
    const userImage = document.getElementById("userImage");
    if (userImage) {
        userImage.addEventListener("click", function () {
            const userModal = document.getElementById("userModal");
            if (userModal) {
                userModal.classList.toggle("show");
            } else {
                console.error("User modal not found");
            }
        });
    }

    // 마이페이지 버튼 클릭 이벤트
    const myPageButton = document.getElementById("myPageButton");
    if (myPageButton) {
        myPageButton.addEventListener("click", function () {
            window.location.href = "/mypage";
        });
    }

    // 전체 선택/해제
    const selectAllCheckbox = document.getElementById("selectAll");
    selectAllCheckbox.addEventListener("change", function () {
        const checkboxes = document.querySelectorAll(".post-checkbox");
        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });

    // 게시물 데이터 조회
    async function fetchPosts(category) {
        try {
            const form = new FormData();
            form.append('category', category);

            const response = await fetch("/admin/board/list/reportBoard", {
                method: "POST",
                body: form
            });

            if (!response.ok) {
                throw new Error('Failed to fetch posts');
            }

            const html = await response.text();
            document.getElementById("postListBody").innerHTML = html;
        } catch (error) {
            console.error('Error:', error);
        }
    }

    // 검색 버튼 클릭 이벤트
    const searchButton = document.getElementById("searchButton");
    searchButton.addEventListener("click", function (e) {
        e.preventDefault();
        const searchCategory = document.getElementById("boardSelect").value;
        fetchPosts(searchCategory);
    });

    // 페이지당 게시물 수 변경 이벤트
    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    itemsPerPageSelect.addEventListener("change", function () {
        const searchCategory = document.getElementById("boardSelect").value;
        fetchPosts(searchCategory);
    });

    // 초기 데이터 로드
    // fetchPosts(category || "신고");
});