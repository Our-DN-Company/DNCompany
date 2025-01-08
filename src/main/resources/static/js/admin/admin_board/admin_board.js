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
    // const searchButton = document.getElementById("searchButton");
    // searchButton.addEventListener("click", function (e) {
    //     e.preventDefault();
    //     const searchCategory = document.getElementById("boardSelect").value;
    //     fetchPosts(searchCategory);
    // });

    // 검색 폼 제출 처리
    const searchForm = document.getElementById("searchForm");
    searchForm.addEventListener("submit", async function(e) {
        e.preventDefault();
        const formData = new FormData(searchForm);

        try {
            const response = await fetch("/admin/board/list/reportBoard", {
                method: "POST",
                body: formData
            });

            if (!response.ok) throw new Error('검색 실패');

            const html = await response.text();
            document.getElementById("postListBody").innerHTML = html;
        } catch (error) {
            console.error('Error:', error);
        }
    });

    // 날짜 버튼 클릭 처리
    const dateButtons = document.querySelectorAll('.date-btn');
    dateButtons.forEach(btn => {
        btn.addEventListener('click', function() {
            const today = new Date();
            const endDate = document.getElementById('endDate');
            const startDate = document.getElementById('startDate');

            // 종료일은 항상 오늘
            endDate.valueAsDate = today;

            // 시작일 계산
            switch(this.textContent) {
                case '오늘':
                    startDate.valueAsDate = today;
                    break;
                case '3일':
                    startDate.valueAsDate = new Date(today.setDate(today.getDate() - 3));
                    break;
                case '7일':
                    startDate.valueAsDate = new Date(today.setDate(today.getDate() - 7));
                    break;
                case '1개월':
                    startDate.valueAsDate = new Date(today.setMonth(today.getMonth() - 1));
                    break;
            }
        });
    });



    // 페이지당 게시물 수 변경 이벤트
    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    itemsPerPageSelect.addEventListener("change", function () {
        const searchCategory = document.getElementById("boardSelect").value;
        fetchPosts(searchCategory);
    });

    // 초기 데이터 로드
    fetchPosts(category || "all");
});

// 모달 코드

document.addEventListener('DOMContentLoaded', function() {
    const postListBody = document.getElementById('postListBody');
    const postDetailModal = document.getElementById('postDetailModal');
    const closeModalBtn = document.querySelector('.close-btn');
    const submitReplyBtn = document.getElementById('submitReplyBtn');

    // 모달 외부 클릭 시 모달 닫기
    postDetailModal.addEventListener('click', function(e) {
        if (e.target === postDetailModal) {
            postDetailModal.style.display = 'none';
        }
    });

    // 제목 또는 답변처리 칸 클릭 시 모달 오픈
    postListBody.addEventListener('click', function(e) {
        const titleCell = e.target.closest('td:nth-child(4)');
        const statusCell = e.target.closest('td:nth-child(5)');

        if (titleCell || statusCell) {
            const row = e.target.closest('tr');
            const postId = row.getAttribute('data-post-id');
            const category = row.getAttribute('data-category');


            // QnA가 아닐 경우 얼럿 표시하고 반환
            // 임시로 나머지 카테고리 설정 안해놈
            // 그래서 강제로 막기 처리
            if (category !== 'QNA') {
                alert('QnA 게시글만 조회할 수 있습니다.');
                return;
            }

            console.log("Clicked row data:", { postId, category }); // 디버깅용

            // 모달에 정보 채우기
            document.getElementById('modalPostCategory').textContent = category;
            document.getElementById('modalPostTitle').textContent = row.querySelector('td:nth-child(4)').textContent;
            document.getElementById('modalPostWriter').textContent = row.querySelector('td:nth-child(6)').textContent;
            document.getElementById('modalPostDate').textContent = row.querySelector('td:nth-child(9)').textContent;

            // 현재 게시글 정보를 모달에 저장
            postDetailModal.setAttribute('data-post-id', postId);
            postDetailModal.setAttribute('data-category', category);

            // QnA 게시글일 경우 내용과 답변 로드
            if (category === 'QNA') {
                loadQnaDetail(postId);
                loadReplies(postId, category);
            }

            postDetailModal.style.display = 'block';
        }
    });

    // QnA 상세 내용 로드 함수
    async function loadQnaDetail(qnaId) {
        try {
            console.log("QnA ID:", qnaId);
            const response = await fetch(`/admin/board/qna/detail?qnaId=${qnaId}`);
            if (!response.ok) throw new Error('상세 내용 로드 실패');
            const data = await response.json();
            console.log("받은 데이터:", data);  // 서버에서 받은 데이터 확인

            // 내용이 있는지 확인
            // 아니 이녀석 별칭도 줬는데 왜 대문자로 쳐 오는거지 ?
            // 대문자로 쳐와서 내용값을 못 찾았음
            // 위에 로그는 전부 COnTENT 찾을려고 설정해논 것들
            if (data && data.CONTENT) {
                document.getElementById('modalPostContent').textContent = data.CONTENT;
            } else {
                console.log("내용 없음:", data);
                document.getElementById('modalPostContent').textContent = '내용이 없습니다.';
            }
        } catch (error) {
            console.error('상세 내용 로드 실패:', error);
            document.getElementById('modalPostContent').textContent = '내용을 불러오지 못했습니다.';
        }
    }

    // 답변 로드 함수
    async function loadReplies(postId, category) {
        const repliesContainer = document.getElementById('existingReplies');
        try {
            const response = await fetch(`/admin/board/replies?postId=${postId}&category=${category}`);
            const replies = await response.json();

            if (replies.length > 0) {
                repliesContainer.innerHTML = replies.map(reply => `
                       <div class="reply">
                           <p>${reply.qnaAnswerContent}</p>
                           <small>${new Date(reply.qnaAnswerCreatedAt).toLocaleString()}</small>
                       </div>
                   `).join('');
            } else {
                repliesContainer.innerHTML = `
                       <div class="reply">
                           <p>아직 등록된 댓글/답변이 없습니다.</p>
                       </div>
                   `;
            }
        } catch (error) {
            console.error('댓글 로드 실패:', error);
            repliesContainer.innerHTML = `
                   <div class="reply">
                       <p>댓글을 불러오는 중 오류가 발생했습니다.</p>
                   </div>
               `;
        }
    }

    // 답변 등록 버튼 이벤트
    submitReplyBtn.addEventListener('click', function() {
        const replyContent = document.getElementById('replyTextarea').value;
        const postId = postDetailModal.getAttribute('data-post-id');
        const category = postDetailModal.getAttribute('data-category');

        console.log("Submitting reply:", { postId, category, content: replyContent }); // 디버깅용

        // 상세 로깅 추가 버그잡기 쌍놈시키
        console.log("전송 데이터 확인:", {
            qnaId: postId,
            qnaAnswerContent: replyContent,
            category: category
        });

        if (!replyContent.trim()) {
            alert('답변 내용을 입력해주세요.');
            return;
        }

        if (category === 'QNA') {
            const requestData = {
                qnaId: Number(postId),
                category: category,
                qnaAnswerContent: replyContent
            };

            console.log('서버로 전송할 데이터:', requestData); // 로그 추가 모르겠다 버그야~~ 그만 하고 나와라~~~

            fetch('/admin/board/reply', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                    body: JSON.stringify(requestData)

            })
                .then(response => response.json())
                .then(data => {
                    if (data.error) throw new Error(data.error);
                    alert('답변이 등록되었습니다.');
                    loadReplies(postId, category);
                    document.getElementById('replyTextarea').value = '';
                    const statusCell = document.querySelector(`tr[data-post-id="${postId}"] td:nth-child(5)`);
                    if (statusCell) {
                        statusCell.textContent = 'O';
                    }
                })
                .catch(error => {
                    console.error('답변 등록 실패:', error);
                    alert('답변 등록에 실패했습니다: ' + error.message);
                });
        }
    });

    // 모달 닫기 버튼
    closeModalBtn.addEventListener('click', function() {
        postDetailModal.style.display = 'none';
    });
});
