document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);
    const category = urlParams.get('category'); // category 값에 따라 셀렉트 박스의 선택 항목을 설정합니다.
    if (category) {
        const boardSelect = document.getElementById("boardSelect");
        if (boardSelect) {
            boardSelect.value = category;
        }
    }


    // userImage 클릭 시 userModal 표시/숨김
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

    // myPageButton 클릭 시 페이지 이동
    const myPageButton = document.getElementById("myPageButton");
    if (myPageButton) {
        myPageButton.addEventListener("click", function () {
            window.location.href = "/mypage";
        });
    }

    // 전체 선택/해제 기능
    const selectAllCheckbox = document.getElementById("selectAll");
    selectAllCheckbox.addEventListener("change", function () {
        const checkboxes = document.querySelectorAll(".post-checkbox");
        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });

    // 임시 게시물 데이터 추가 (데이터베이스 연결 시 삭제 필요)
    const postListBody = document.getElementById("postListBody");
    const posts = [
        {
            number: 1,
            category: "QnA",
            title: "게시물 제목 1",
            answered: "완료",
            author: "작성자1",
            email: "KimJava@naver.com",
            phone: "010-1234-5678",
            date: "2024-12-15"
        },
        {
            number: 2,
            category: "신고",
            title: "게시물 제목 2",
            answered: "진행 중",
            author: "작성자2",
            email: "ParkPython@naver.com",
            phone: "010-2345-6789",
            date: "2024-12-14"
        },
        {
            number: 3,
            category: "이벤트",
            title: "게시물 제목 3",
            answered: "대기 중",
            author: "작성자3",
            email: "LeeJavaScript@naver.com",
            phone: "010-3456-7890",
            date: "2024-12-13"
        },
        {
            number: 4,
            category: "QnA",
            title: "게시물 제목 4",
            answered: "완료",
            author: "작성자4",
            email: "author4@example.com",
            phone: "010-4567-8901",
            date: "2024-12-12"
        },
        {
            number: 5,
            category: "신고",
            title: "게시물 제목 5",
            answered: "진행 중",
            author: "작성자5",
            email: "author5@example.com",
            phone: "010-5678-9012",
            date: "2024-12-11"
        },
        {
            number: 6,
            category: "이벤트",
            title: "게시물 제목 6",
            answered: "대기 중",
            author: "작성자6",
            email: "author6@example.com",
            phone: "010-6789-0123",
            date: "2024-12-10"
        },
        {
            number: 7,
            category: "QnA",
            title: "게시물 제목 7",
            answered: "완료",
            author: "작성자7",
            email: "author7@example.com",
            phone: "010-7890-1234",
            date: "2024-12-09"
        },
        {
            number: 8,
            category: "신고",
            title: "게시물 제목 8",
            answered: "진행 중",
            author: "작성자8",
            email: "author8@example.com",
            phone: "010-8901-2345",
            date: "2024-12-08"
        },
        {
            number: 9,
            category: "이벤트",
            title: "게시물 제목 9",
            answered: "대기 중",
            author: "작성자9",
            email: "author9@example.com",
            phone: "010-9012-3456",
            date: "2024-12-07"
        },
        {
            number: 10,
            category: "QnA",
            title: "게시물 제목 10",
            answered: "완료",
            author: "작성자10",
            email: "author10@example.com",
            phone: "010-0123-4567",
            date: "2024-12-06"
        },
        {
            number: 11,
            category: "신고",
            title: "게시물 제목 11",
            answered: "진행 중",
            author: "작성자11",
            email: "author11@example.com",
            phone: "010-1234-5678",
            date: "2024-12-05"
        },
    ];

    // 게시물 렌더링 함수
    function renderPosts(postsToShow) {
        postListBody.innerHTML = ""; // 기존 게시물 목록 초기화
        postsToShow.forEach((post) => {
            const row = document.createElement("tr");
            row.innerHTML = `
          <td><input type="checkbox" class="post-checkbox"></td>
          <td>${post.number}</td>
          <td>${post.category}</td>
          <td>${post.title}</td>
          <td>${post.answered}</td>
          <td>${post.author}</td>
          <td>${post.email}</td>
          <td>${post.phone}</td>
          <td>${post.date}</td>
        `;
            postListBody.appendChild(row);
        });
    }

    // 초기 게시물 목록 렌더링
    renderPosts(posts);

    // 검색 버튼 클릭 시 게시물 필터링
    const searchButton = document.getElementById("searchButton");
    searchButton.addEventListener("click", function () {
        const searchCategory = document.getElementById("boardSelect").value;
        const searchType = document.getElementById("searchType").value;
        const searchInput = document.getElementById("searchInput").value.toLowerCase();

        // 검색 조건에 맞는 게시물 필터링
        const filteredPosts = posts.filter((post) => {
            // 카테고리 필터링
            const matchesCategory = searchCategory === "all" ||
                post.category.toLowerCase() === searchCategory.toLowerCase();

            // 제목, 내용, 작성자, 아이디 필터링
            let matchesSearch = false;
            if (searchType === "title") {
                matchesSearch = post.title.toLowerCase().includes(searchInput);
            } else if (searchType === "author") {
                matchesSearch = post.author.toLowerCase().includes(searchInput);
            } else if (searchType === "id") {
                matchesSearch = post.email.toLowerCase().includes(searchInput);
            }
            return matchesCategory && matchesSearch;
        });

        renderPosts(filteredPosts); // 필터링된 게시물 렌더링
    });

    // 페이지 당 게시물 수 변경 이벤트 리스너 추가
    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    itemsPerPageSelect.addEventListener("change", function () {
        const itemsPerPage = parseInt(itemsPerPageSelect.value, 10);
        renderPosts(posts.slice(0, itemsPerPage));
    });
});
