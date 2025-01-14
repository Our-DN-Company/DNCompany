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


















