

{
    const $mypageListContainer = document.querySelector('.mypage_main_help_me');

    $mypageListContainer.addEventListener('click', function (e) {
        // console.log(e.target);
        const $tr = e.target.closest('tr');
        const helpId = $tr.dataset.helpId;

        // console.log(helpId);
        location.href = `/help/detail?helpId=${helpId}`;
    });
}