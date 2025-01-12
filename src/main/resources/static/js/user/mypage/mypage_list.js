const $postList = document.querySelectorAll('.mypage_list_box"');

$postList.forEach(post => {
    post.addEventListener('click', function () {
        const helpId = this.dataset.helpId;
        location.href=`/help/detail?helpId=${helpId}`;
    });
})
function goToDetail(helpId) {
    if(helpId) {
        location.href = `/help/detail?helpId=${helpId}`;
    }
}