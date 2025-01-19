{ //클릭시 디테일 페이지로 이동
    const $mypageListContainer = document.querySelector('.mypage_list');

    $mypageListContainer.addEventListener('click', function (e) {
        // console.log(e.target);
        const $tr = e.target.closest('tr');
        const helpId = $tr.dataset.helpId;

        // console.log(helpId);
        location.href = `/help/detail?helpId=${helpId}`;
    });
}

