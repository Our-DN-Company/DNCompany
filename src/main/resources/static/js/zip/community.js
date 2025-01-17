{   // 작성 버튼 처리
    const writeButton = document.querySelector(".write_Btn_item");
    if (writeButton) {
        writeButton.addEventListener("click", () => {
            window.location.href = "/zip/write";
        });
    }
}

{   // 정렬 버튼 처리
    const $radioContainer = document.querySelector('.community_radioContainer');

    $radioContainer.addEventListener("change", (e) => {
        if (e.target.classList.contains("radio_myRadio")) {
            const searchParams = new URLSearchParams(location.search);
            searchParams.set("order", e.target.value);

            location.search = searchParams.toString();
        }
    });
}

// document.addEventListener("DOMContentLoaded", () => {
//     const $qaListDateTime = document.querySelectorAll(".qaList_communityType");
//
//     $qaListDateTime.forEach(element => {
//         const updatedAt = element.getAttribute("data-updated-at");
//
//         if (updatedAt) {
//             const formattedTime = timeForToday(updatedAt);
//
//             element.textContent = `수정일 : ${formattedTime}`;
//         }
//     });
// });
//
// function timeForToday(value) {
//     const today = new Date();
//     const timeValue = new Date(value);
//
//
//     const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
//
//     if (betweenTime < 1) { return "방금 전"; }
//
//     if (betweenTime < 60) {
//         return `${betweenTime}분 전`;
//     }
//
//     const betweenTimeHour = Math.floor(betweenTime / 60);
//     if (betweenTimeHour < 24) {
//         return `${betweenTimeHour}시간 전`;
//     }
//
//     const betweenTimeDay = Math.floor(betweenTimeHour / 24);
//     if (betweenTimeDay < 365) {
//         return `${betweenTimeDay}일 전`;
//     }
//
//     return `${Math.floor(betweenTimeDay / 365)}년 전`;
// }


















