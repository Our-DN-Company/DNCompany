document.addEventListener("DOMContentLoaded", function () {
    // 모든 "수락하기" 버튼에 이벤트 리스너 추가
    document.querySelectorAll(".help-you-btn").forEach(button => {
        button.addEventListener("click", function () {
            const form = this.closest(".accept-form"); // 버튼과 연결된 폼 가져오기

            // 알림창 표시
            const confirmResult = confirm("수락하시겠습니까?");

            // 사용자가 확인을 누르면 폼 제출
            if (confirmResult) {
                alert("수락되었습니다!");
                form.submit();
            }
        });
    });
});